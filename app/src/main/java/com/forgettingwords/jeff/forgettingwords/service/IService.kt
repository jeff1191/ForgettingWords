package com.forgettingwords.jeff.forgettingwords.service

import com.forgettingwords.jeff.forgettingwords.model.DataModel

interface IService {

    fun onCreate() : List<DataModel>
}