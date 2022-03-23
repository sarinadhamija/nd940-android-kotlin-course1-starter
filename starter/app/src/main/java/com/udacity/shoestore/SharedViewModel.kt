package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedViewModel : ViewModel() {

    private val imageList = ArrayList<Int>()
    private var email : String? = null

    private val _shoeListLiveData = MutableLiveData<ArrayList<Shoe>>()
    val shoeListLiveData: LiveData<ArrayList<Shoe>>
        get() = _shoeListLiveData



    private fun getImageList() : List<Int>{
        imageList.shuffle()
        return imageList
    }

    fun getImage() = getImageList().get(0)

    fun addShow(shoe : Shoe){
        _shoeListLiveData.value?.add(shoe)
    }

    fun setImageList(list : MutableList<Int>){
        this.imageList.clear()
        this.imageList.addAll(list)
    }

    fun saveLoginDetails(email : String){
        this.email = email
    }

    fun isUserLoggedIn() = !email.isNullOrEmpty()

    fun getEmail() = email

    fun initializeShoeList(){
        val shoeList = ArrayList<Shoe>()
        shoeList.add(Shoe("Name 1", 20, "Company 1", "Description", getImageList()[0]))
        shoeList.add(Shoe("Name 2", 21, "Company 1", "Description", getImageList()[1]))
        shoeList.add(Shoe("Name 3", 23, "Company 1", "Description", getImageList()[2]))
        shoeList.add(Shoe("Name 4", 24, "Company 1", "Description", getImageList()[3]))
        shoeList.add(Shoe("Name 5", 25, "Company 1", "Description", getImageList()[4]))
        shoeList.add(Shoe("Name 6", 26, "Company 1", "Description", getImageList()[0]))
        shoeList.add(Shoe("Name 7", 27, "Company 1", "Description", getImageList()[1]))
        shoeList.add(Shoe("Name 8", 28, "Company 1", "Description", getImageList()[2]))
        shoeList.add(Shoe("Name 9", 29, "Company 1", "Description", getImageList()[3]))
        shoeList.add(Shoe("Name 10", 20, "Company 1", "Description", getImageList()[4]))
        shoeList.add(Shoe("Name 11", 21, "Company 1", "Description", getImageList()[0]))
        shoeList.add(Shoe("Name 12", 22, "Company 1", "Description", getImageList()[1]))
        shoeList.add(Shoe("Name 13", 23, "Company 1", "Description", getImageList()[2]))
        shoeList.add(Shoe("Name 14", 24, "Company 1", "Description", getImageList()[3]))
        shoeList.add(Shoe("Name 15", 25, "Company 1", "Description", getImageList()[4]))

        _shoeListLiveData.value = shoeList
    }

}