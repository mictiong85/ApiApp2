package jp.techacademy.thion.maikeru.apiapp2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity: AppCompatActivity() {

    private val items= mutableListOf<Shop>()
    var url:String=""
    var onClickAddFavorite:((Shop)->Unit)?=null
    var onClickDeleteFavorite:((Shop)->Unit)?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)


        val shop1 = intent.getSerializableExtra(KEY_URL) as Shop
        val data = shop1
        val isFavorite = FavoriteShop.findBy(data.id) != null
        url = if (shop1.couponUrls.sp.isNotEmpty()) shop1.couponUrls.sp else shop1.couponUrls.pc
        webView.loadUrl(url)
        if(isFavorite){
            button1.text="削除"
        }else{
            button1.text="登録"
        }

        button1.setOnClickListener{v->
            if(isFavorite){
                //onClickDeleteFavorite?.invoke(data)
                FavoriteShop.delete(data.id)
                button1.text="削除済み"
                Log.d("Test","Im here")
            }else{
                //onClickAddFavorite?.invoke(data)

                FavoriteShop.insert(FavoriteShop().apply {
                    id = data.id
                    name = data.name
                    imageUrl = data.logoImage
                    url = if (data.couponUrls.sp.isNotEmpty()) data.couponUrls.sp else data.couponUrls.pc
                })
                button1.text="登録済み"
                Log.d("Test","Im here2")
            }

        }

    }


    companion object {
        private const val KEY_URL = "key_url"
        fun start(activity: Activity, shop: Shop) {
            activity.startActivity(Intent(activity, WebViewActivity::class.java).putExtra(KEY_URL,shop))
        }

    }

}