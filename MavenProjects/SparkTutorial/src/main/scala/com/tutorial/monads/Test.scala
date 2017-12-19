package com.tutorial.monads

object Test {


  def maternalGrandfather(p: Person): Maybe[Person] =
    p.mother flatMap { _.father }

  def maternalGrandfatherNoFlatMap(p: Person): Maybe[Person] =
    p.mother match {
      case Just(m) => m.father
      case MaybeNot => MaybeNot
    }


  def main(args: Array[String]) {


    Person.persons foreach { p =>
     println( "" + p + assert(maternalGrandfather(p) == maternalGrandfatherNoFlatMap(p)) )

      println( ".. " + p + maternalGrandfather(p) )

    }

  }

}



