package com.example.project.models

class Transaction{
    var id :String =""
    var amount: Double=0.0
    var date : String = ""
    var category:String =""
    var type: String =""
   constructor()
constructor(amount: Double, type:String, category:String, date:String, id: String){
    this.id=id
    this.amount=amount
    this.date=date
    this.category=category
    this.type=type


}
}
class Balance{
    var current= 0.0
}

