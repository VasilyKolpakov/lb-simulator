package ru.vasily.trash

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class MonadsTest extends FlatSpec with ShouldMatchers {


  "A StateMonad" should "preserve state" in {

    trait StateMonad[S, V] {
      def map[B](f: (V) => B): StateMonad[S, B] = StateMonad {
        state => {
          val (newState, value) = apply(state)
          (newState, f(value))
        }
      }

      def flatMap[B](f: (V) => StateMonad[S, B]) = StateMonad {
        state: S => {
          val (newState, value) = apply(state)
          f(value)(newState)
        }
      }

      def apply(state: S): (S, V)
    }

    object StateMonad {
      def apply[S, V](f: (S) => (S, V)) = new StateMonad[S, V] {
        def apply(s: S) = f(s)
      }
    }

    def getByKey(key: String) = StateMonad {
      keys: List[String] => (key :: keys, key)
    }

    val monad = for {
      dep1 <- getByKey("dep1")
      dep2 <- getByKey("dep2")
    } yield Map("dep1" -> dep1, "dep2" -> dep2)

    val (list, obj) = monad(Nil)
    obj should equal(Map("dep1" -> "dep1", "dep2" -> "dep2"))
    list should equal("dep2" :: "dep1" :: Nil)
  }
}