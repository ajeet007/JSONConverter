package jsonconverter

class TestController {

    def index() {
        println fact(5)
        println factL(500)
        println fib(5)
        println fibL(500)

    }
    def fact={n->
        if(n==1)
            1
        else
            n*fact(n-1)
    }

    def factL={n->
        if(n==1)
            1
        else
            n*factL(n-1)
    }

    def fib={n->
        if(n<3)
            1
        else
            fib(n-1)+fib(n-2)
    }

    def fibL={n->
        if(n<3)
            1
        else
            fibL(n-1)+fibL(n-2)
    }.memoize()
}
