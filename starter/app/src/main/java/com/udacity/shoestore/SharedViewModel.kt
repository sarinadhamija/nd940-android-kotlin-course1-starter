package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedViewModel : ViewModel() {

    private val imageList = ArrayList<Int>()
    var email: String? = null
    var shoe = Shoe()

    private var _isDataSuccessfullySaved = MutableLiveData<Boolean>()
    val isDataSuccessfullySaved: LiveData<Boolean>
        get() = _isDataSuccessfullySaved

    private var _isFieldEmpty = MutableLiveData<Boolean>()
    val isFieldEmpty: LiveData<Boolean>
        get() = _isFieldEmpty

    private val _shoeListLiveData = MutableLiveData<ArrayList<Shoe>>()
    val shoeListLiveData: LiveData<ArrayList<Shoe>>
        get() = _shoeListLiveData

    init {
        _isFieldEmpty.value = false
        _isDataSuccessfullySaved.value = false
    }

    private fun getImageList(): List<Int> {
        imageList.shuffle()
        return imageList
    }

    fun getImage() = getImageList().get(0)

    fun addShoe() {
        if (this.shoe.name.isNullOrEmpty() ||
            this.shoe.company.isNullOrEmpty() ||
            this.shoe.description.isNullOrEmpty() ||
            this.shoe.size == 0) {
            _isFieldEmpty.value = true
        } else {
            shoe.image = getImage()
            _shoeListLiveData.value?.add(shoe)
            _isDataSuccessfullySaved.value = true
        }
    }

    fun updateDataSuccessFlag(value : Boolean) {
        _isDataSuccessfullySaved.value = false
    }

    fun setImageList(list: MutableList<Int>) {
        this.imageList.clear()
        this.imageList.addAll(list)
    }

    fun isUserLoggedIn() = !email.isNullOrEmpty()

    fun initializeShoeList() {
        val shoeList = ArrayList<Shoe>()
        shoeList.add(Shoe("Athletic Shoe", 20, "Adidas", "Description", getImageList()[0]))
        shoeList.add(Shoe("Ballet Shoe", 21, "Clarks", "Description", getImageList()[1]))
        shoeList.add(Shoe("Flip Flops", 23, "Crocs", "Description", getImageList()[2]))
        shoeList.add(Shoe("Clogs", 24, "Metro", "Description", getImageList()[3]))
        shoeList.add(Shoe("Oxford Shoes", 25, "Catwalk", "Description", getImageList()[4]))
        shoeList.add(Shoe("Ankle Boots", 26, "Khadims", "Description", getImageList()[0]))
        shoeList.add(Shoe("Knee-length Boots", 27, "Clarks", "Description", getImageList()[1]))
        shoeList.add(Shoe("Sneakers", 28, "Metro", "Description", getImageList()[2]))
        shoeList.add(Shoe("Moccasins", 29, "Khadims", "Description", getImageList()[3]))
        shoeList.add(Shoe("Slide Sandal", 20, "Clarks", "Description", getImageList()[4]))
        shoeList.add(Shoe("Slippers", 21, "Catwalk", "Description", getImageList()[0]))
        shoeList.add(Shoe("Snow Boots", 22, "Metro", "Description", getImageList()[1]))
        shoeList.add(Shoe("Kitten Heel", 23, "Khadims", "Description", getImageList()[2]))
        shoeList.add(Shoe("Mule", 24, "Clarks", "Description", getImageList()[3]))
        shoeList.add(Shoe("Bow Sandals", 25, "Metro", "Description", getImageList()[4]))

        _shoeListLiveData.value = shoeList
    }
}