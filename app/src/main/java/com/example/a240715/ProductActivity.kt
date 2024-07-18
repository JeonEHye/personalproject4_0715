package com.example.a240715

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a240715.databinding.ActivityProductBinding
import java.lang.NumberFormatException
import java.text.NumberFormat

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product)

        //View Binding 설정
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Intent 데이터 수신 (값 가져오기)

        // 1번
//        val position = intent.getStringExtra("position")?.toIntOrNull() ?: return
//
//         2번
        val product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("products", MyItem::class.java)
        } else {
            intent.getParcelableExtra("products")
        }
        product ?: return


//        1번
//        val product = ProductManager.products.get(position)

        // 1번, 2번 공통
        // binding.레이아웃.  .myitem

        // aPrice값을 문자열로 변환하여 priceString 변수에 저장
        var priceString = product.aPrice.toString()
        var productActivityPrice = ""

        // 가격 문자열의 길이가 4 이상인지 확인
        if(priceString.length >= 4){

            //만약 길이가 4 이상일 경우
            try {
                // 문자열을 Long 타입으로 변환
                val priceNumber = priceString.toLong()
                // 변환한 숫자를 포맷팅하여 쉼표를 추가하고 원을 붙임(price에 천 단위 쉼표 넣기)
                productActivityPrice = NumberFormat.getInstance().format(priceNumber).toString() + "원"
            } catch (e: NumberFormatException){ //예외가 발생하면 포맷팅하지 않고 원래 문자열을 사용
                productActivityPrice = priceString
            }
        } else { //가격 문자열의 길이가 4 미만일 경우
            productActivityPrice = priceString + "원"
        }

        binding.imageView.setImageResource(product.aIcon1)
        binding.tvSeller.text = product.aSeller
        binding.tvAddress.text = product.aAddress
        binding.tvTitle.text=product.aTitle
        binding.tvContent.text=product.aContext
        binding.tvPrice.text=productActivityPrice


    }
}