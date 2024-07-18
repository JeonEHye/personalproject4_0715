package com.example.a240715

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a240715.databinding.ItemRecyclerviewBinding
import java.lang.NumberFormatException
import java.text.NumberFormat

class MyAdapter (val mItems: MutableList<MyItem>): RecyclerView.Adapter<MyAdapter.Holder>() {

    //Main-Adapter 사이 통신 인터페이스
    interface ItemClick{
        fun onClick(view: View, position: Int)
    }

    // 통신을 위한 인터페이스 타입 변수 선언
    var itemClick : ItemClick? = null

    // ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.Holder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    // Holder가 임시로 갖고 있던 레이아웃에 실제 원본 데이터 값 할당
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyAdapter.Holder, position: Int) {
        holder.itemView.setOnClickListener{
            // RecyclerView-itemView 전체에 onClickListener를 설정
            // itemView 전체에 클릭이 발생하면, it(View)와 position값을 onClick에 넣어서 itemClick을 호출
            itemClick?.onClick(it, position)
        }

        //val aIcon1:Int, val aName:String, val aAddress:String, val aPrice1:String, val aEtc1:String, val aEtc2:String

        holder.iconImageView.setImageResource(mItems[position].aIcon1)
        holder.name.text = mItems[position].aTitle
        holder.address.text = mItems[position].aAddress

        // holder.price.text에 aPrice 값을 직접 할당
        holder.price.text = mItems[position].aPrice
        // aPrice값을 문자열로 변환하여 priceString 변수에 저장
        val priceString = mItems[position].aPrice.toString()

        // 가격 문자열의 길이가 4 이상인지 확인
        if(holder.price.text.length >= 4){

            //만약 길이가 4 이상일 경우
            try {
                // 문자열을 Long 타입으로 변환
                val priceNumber = priceString.toLong()
                // 변환한 숫자를 포맷팅하여 쉼표를 추가하고 원을 붙임(price에 천 단위 쉼표 넣기)
                holder.price.text = NumberFormat.getInstance().format(priceNumber).toString() + "원"
            } catch (e: NumberFormatException){ //예외가 발생하면 포맷팅하지 않고 원래 문자열을 사용
                holder.price.text = priceString
            }
        } else { //가격 문자열의 길이가 4 미만일 경우
            holder.price.text = holder.price.text.toString() + "원"
        }


        holder.etc1.text = mItems[position].aEtc1
        holder.etc2.text = mItems[position].aEtc2
    }

    override fun getItemId(position: Int): Long{
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(val binding:ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root){
        val iconImageView = binding.iconItem
        val name = binding.textItem1
        val address= binding.textItem2
        val price=binding.textItem3
        val etc1=binding.textItem4
        val etc2=binding.textItem5
    }
}