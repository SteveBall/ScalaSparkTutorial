package com.tutorial.monads

object Person {

  val persons: List[Person] = List("P", "MP", "MMP", "FMP", "FP", "MFP", "FFP") map { Person(_) }

  private val mothers = Map(
    Person("P") -> Person("MP"),
    Person("MP") -> Person("MMP"),
    Person("FP") -> Person("MFP"))

  private val fathers = Map(
    Person("P") -> Person("FP"),
    Person("MP") -> Person("FMP"),
    Person("FP") -> Person("FFP"))

  def mother(p: Person): Maybe[Person] = relation(p, mothers)

  def father(p: Person): Maybe[Person] = relation(p, fathers)

  private def relation(p: Person, relationMap: Map[Person, Person]) = relationMap.get(p) match {
    case Some(m) => Just(m)
    case None => MaybeNot
  }
}

case class Person(name: String) {
  def mother: Maybe[Person] = Person.mother(this)
  def father: Maybe[Person] = Person.father(this)
}





