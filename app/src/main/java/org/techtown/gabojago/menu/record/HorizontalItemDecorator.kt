import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
//리사이클러뷰 크기 핸드폰 화면 크기 기준으로 맞추는 클래스
class HorizontalItemDecorator(private val divHeight : Int) : RecyclerView.ItemDecoration() {

    @Override
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left  = divHeight
        outRect.right  = divHeight
    }
}