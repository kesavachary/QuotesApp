package com.example.quotesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context): ViewModel() {
    //declare variable quoteList that is array type,and it is declared as empty array
    private var quoteList: Array<Quote> = emptyArray()
    //assign initial index value 0.
    private var index = 0
    //initialize quoteList to a function it returns Array of Quote implementation done below
    init {
        //loadQuoteFromAssets function implementation below
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quote> {
        //read quotes from assets folder.any information pass in mainViewModel we need factory so pass the context of our app
        val inputStream = context.assets.open("quotes.json") //It open file which is in assets folder by using context
        val size: Int = inputStream.available() //It defines our file size by using available()(Returns an estimate of number of bytes that can be read)
        val buffer = ByteArray(size) //create buffer it stores our filesize,it holds the ByteArray type
        inputStream.read(buffer) //for read our file
        inputStream.close()      //for close the file
        val json = String(buffer, Charsets.UTF_8) //converts ByteArray of our file into Strings type(encoding)
        val gson = Gson()                         //create instance for Gson()
        return gson.fromJson(json, Array<Quote>::class.java) //Type casting convert the Strings type to kotlin objects
    }

    fun getQuote() = quoteList[index] //Return quotes on the basis of current index from Array of Quotes

    fun nextQuote() = quoteList[index++ % quoteList.size] //Return next Quotes

    fun previousQuote() = quoteList[(index-- + quoteList.size) % quoteList.size] //Return previous Quotes
}