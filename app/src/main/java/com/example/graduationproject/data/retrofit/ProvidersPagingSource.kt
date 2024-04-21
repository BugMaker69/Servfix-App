import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.graduationproject.data.ReturnedProviderData
import com.example.graduationproject.data.repositories.FindProviderSearchReopsitory


class ProviderPagingSource(
    private val repo: FindProviderSearchReopsitory,
    private var id: Int,
    private var query: String = "",
    private var city:String="",
    private var rating:Int=0
) : PagingSource<Int, ReturnedProviderData>() {
    private val BASE_URL = "https://p2kjdbr8-8000.uks1.devtunnels.ms/api"

    override fun getRefreshKey(state: PagingState<Int, ReturnedProviderData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            try {
                val anchorPage = state.closestPageToPosition(anchorPosition)
                anchorPage?.prevKey?.plus(1) ?: if (anchorPage?.data?.isEmpty() != true) anchorPage?.nextKey else null
            }catch (ex:Exception){
                Log.d("memlord", "getRefreshKey:${ex.message.toString()} ")
            }

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReturnedProviderData> {
        return try {
            val pageNum = params.key ?: 1
            Log.d("memlord", "load: ${id.toString()}")
            Log.d("memlord", "load: page num ${pageNum}")

            val response = if (query.isBlank()&&city.isBlank()&&rating==0) {
                repo.getProvidersList(id = id, pageNum = pageNum)
            } else {
                repo.searchOnList(id = id, keyword = query, minRate = rating, place = city,pageNum=pageNum)
            }
            Log.d("memlord", "load: responc ${response.size}")

            response.forEach {
                it.image = BASE_URL + it.image
            }

            val nextKey = if (response.isEmpty()) null else pageNum + 1

            LoadResult.Page(
                data = response,
                prevKey = if (pageNum == 1) null else pageNum - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            Log.d("memlord", "load:${e.message.toString()} ")
            LoadResult.Error(e)
        }
    }
}
