package com.tutorial.monads


sealed trait Maybe[+A] {

  // >>=
 // def flatMap[B](f: A => Maybe[B]): Maybe[B]

  // >>=
  def flatMap[B](f: A => Maybe[B]): Maybe[B] = this match {
    case Just(a) => f(a)
    case MaybeNot => MaybeNot
  }
}

case class Just[+A](a: A) extends Maybe[A] {
  override def flatMap[B](f: A => Maybe[B]): Maybe[B] = f(a)
}

// Nothing in the Haskel example
case object MaybeNot extends Maybe[Nothing] {
  override def flatMap[B](f: Nothing => Maybe[B]): MaybeNot.type = MaybeNot
}
