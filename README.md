# Kotlin-Publish-Subscribe 

[![](https://jitpack.io/v/DavidMellul/Kotlin-Publish-Subscribe.svg)](https://jitpack.io/#DavidMellul/Kotlin-Publish-Subscribe)

🦄Intuitive and powerful human-readable Kotlin DSL for IPCs & turning anything into a message receiver / broadcaster🦄

:white_check_mark: Seamless integration <br />
:white_check_mark: No dependencies <br />
:white_check_mark: No modification in your code & Powerful human-readable DSL<br />
:white_check_mark: Lightweight library ~ 1kb <br />


## <a href="#demonstration"></a>Demonstration

#### Simple emitter - receiver

```kotlin
val alice = "Alice"
val bob = "Bob"

alice listenTo "message" then { print("I'm $alice and I have a new message\n") }
bob broadcast "message"

// Output : I'm Alice and I have a new message
```

#### Simple emitter - multiple receivers
```kotlin
val alice = "Alice"
val bryan = "Bryan"
val kevin = "Kevin"

val bob = "Bob"

listOf(alice,bryan,kevin).forEach { receiver -> receiver listenTo "message" then { print("$receiver has received Bob's message!\n") } }
bob broadcast "message"

// Output : 
Bryan has received Bob's message!
Kevin has received Bob's message!
Alice has received Bob's message!
```

#### Multiple emitters - Simple receiver
```kotlin
val julia = "Julia"

val bob = "Bob"
val fred = "Fred"
val tom = "Tom"

julia listenTo "seduction" then { print("Maybe another time fellow...\n")}
listOf(bob,fred,tom).forEach { it broadcast "seduction" }

// Output : 
Maybe another time fellow...
Maybe another time fellow...
Maybe another time fellow...
```

#### Emitter - Receiver with one parameter
```kotlin
val alice = "Alice"
val bob = "Bob"

alice listenTo "seduction" then { seductionLevel ->
            if( (seductionLevel as Int) > 9000)
                print("It's over 9k !!!!!")
            else
                print("Not enough...Keep doin' or die tryin'")
         }
        
bob broadcast SophisticatedSignal("seduction", 9001)

// Output : 
It's over 9k !!!!!
```
#### Emitter - Receiver with multiple parameters (not varargs ! Kotlin limitation :heart:)
```kotlin
val alice = "Alice"
val bob = "Bob"

alice listenTo "charming attempt" then { attempt ->
            print(attempt)
}

bob broadcast SophisticatedSignal("charming attempt",
                hashMapOf(
                        "Name" to "Boby Bob",
                        "Seduction level" to 100000,
                        "Height" to 1.7,
                        "Employment" to "Freelance Trainer & Developer ",
                        "Tasks to be done" to listOf("Pull", "Commit", "Push", "Leave the building"))
)
        
// Output
{Seduction level=100000, Height=1.7, Tasks to be done=[Pull, Commit, Push, Leave the building], Employment=Freelance Trainer & Developer , Name=Boby Bob}
```

#### Emitter - Receiver with one parameter and usage of *stopListenTo*
```kotlin
val alice = "Alice"
val bob = "Bob"

alice listenTo "message" then { message ->
    print("Bob: $message\n")
    alice broadcast SophisticatedSignal("reply", "Wassup bro")
}

bob listenTo "reply" then { reply ->
    print("Alice : $reply\n")
    print("(Bob thinking) I will break up with her hahaha ! First block her\n")
    bob stopListenTo "reply"
    print("(Bob thinking) Ok done !\n")
    bob broadcast SophisticatedSignal("message", "Godd bye !")
}

bob broadcast SophisticatedSignal("message", "Hello Alice !")

// Output :
Bob: Hello Alice !
Alice : Wassup bro
(Bob thinking)I will break up with her hahaha ! First block her
(Bob thinking) Ok done !
Bob: Godd bye !
```
## Install

#### Gradle
First add the Jitpack repository in your root **build.gradle** at the end of repositories:
 ```gradle
 allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
 
 ```
Then, copy paste this line into your dependencies
```gradle
compile 'com.github.DavidMellul:Kotlin-Publish-Subscribe:4179a66595'
```

#### Maven
Add the Jitpack repository to your **pom.xml**
```xml
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
</repositories>
```
Then, add the dependency
```xml
<dependency>
	    <groupId>com.github.DavidMellul</groupId>
	    <artifactId>Kotlin-Publish-Subscribe</artifactId>
	    <version>4179a66595</version>
</dependency>
```

#### SBT

Add both lines to your **build.sbt**
```sbt
resolvers += "jitpack" at "https://jitpack.io"
libraryDependencies += "com.github.DavidMellul" % "Kotlin-Publish-Subscribe" % "4179a66595"	
```

## Use case

You could use this lightweight library in such cases:
- Asynchronous treatments
- Communication between independant objects
- Observer-Observable design-pattern
- Basically everything that requires that both entities communicate so that one reacts to another

## Documentation

The library has been created with Kotlin and has no external dependencies. It means it is fully interoperable with Java ! :purple_heart:

:sparkling_heart: **This library is dead easy to use -> Look at the examples in the [demonstration](#demonstration) :sparkling_heart:**

:triangular_flag_on_post: **This library works only with the Any supertype ==> It means you can turn absolutely everything into a broadcaster / receiver and put everything as parameter associated to a signal.** :triangular_flag_on_post:

## Contribute

Feel free to:

- Open issues / pull requests if there is any bug / missing feature. :love_letter:
- Star this repo :unicorn:
- Ask for a :coffee: 



