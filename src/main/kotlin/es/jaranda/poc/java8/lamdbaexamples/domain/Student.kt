
package es.jaranda.poc.java8.lamdbaexamples.domain

data class Student(
    val name : String,
    val age : Int,
    val professors : List<Professor>
)