# COMP1110/1140/6710 2020 S2 Exam

This repo contains an IntelliJ project for the 2020 S2 exam.

You have **3 hours and 15 mins** to complete the exam.

This exam is open book.   This means that you may use books, notes, and any
other such *pre-existing* information as you complete the exam.   

You **must
not** communicate with any person other than the examiners at any time during
the exam.
  
Chat, text, email and all other such forms of communications **must**
be turned off prior to the exam and must remain off for the **duration of the
exam**.   **The penalties for cheating in an exam at ANU are severe.**

Important notes:
 * There are six (6) questions, each worth different marks.
 * The total marks for the exam are 90 (15 + 5 + 25 + 20 + 5 + 20)
 * The exam will be **entirely auto-graded**, so it is important that your code
  passes the tests, and that you commit and push your work.
 * The CI for the multiple choice question will *not* tell you whether your
 answer is correct, it will only tell you if your answer is *formatted correctly*.
 * There are two practice questions, **P1** and **P2**.   You do  not need to
  complete them.  They are worth **zero** marks.   They are there
 only to help you prepare your environment for the exam.

---




## Question 1  [15 Marks] Imperative Programming in Java
 
### Q1.1  [5 Marks]  Basic Imperative Programming

Using the incomplete template for [Q1Floor.java](src/comp1110/exam/Q1Floor.java),
complete the unimplemented method `findGreater()`.
 
Use the tests provided to test your solution, and then commit and push your
changes to GitLab.

### Q1.2  [5 Marks]  Recursion (harder)

Using the incomplete template for [Q1TwoThree.java](src/comp1110/exam/Q1TwoThree.java),
complete the unimplemented method `twothree()`.
 
Use the tests provided to test your solution, and then commit and push your
changes to GitLab.

### Q1.3  [5 Marks]  Search (harder)

Using the incomplete template for [Q1Word.java](src/comp1110/exam/Q1Word.java),
complete the unimplemented method `find()`.
 
Use the tests provided to test your solution, and then commit and push your
changes to GitLab.

## Q2  [5 Marks]  File I/O, Imperative Programming

Using the incomplete template for [Q2Checksum.java](src/comp1110/exam/Q2Checksum.java),
complete the unimplemented method `checksum()`.
 
Use the tests provided to test your solution, and then commit and push your
changes to GitLab.

## Q3  [25 Marks]

### Q3.1 [20 Marks] Software Implementation

Using the incomplete template for [Q3Hollywood.java](src/comp1110/exam/Q3Hollywood.java),
complete *all unimplemented methods*.
 
You must complete your solution as **a single file, [Q3Hollywood.java](src/comp1110/exam/Q3Hollywood.java)**.
You are encouraged to create additional classes as required to solve the problem;
any additional classes must be implemented as **nested classes** within the
`Q3Hollywood` class.
 
Use the tests provided to test your solution, and then commit and push your
changes to GitLab.

 ### Q3.2  [5 Marks]  Software Testing
 
Using the incomplete template for [Q3GetMaxCoStarsTest.java](src/comp1110/exam/Q3GetMaxCoStarsTest.java),
write one or more unit tests for the `getMaxCoStars()` method. You must complete
your solution as a single file, `Q3GetMaxCoStarsTest.java`. When writing the
tests, you may assume that the provided methods in the `Q3Hollywood` class are
correctly implemented.
 
The specification of the `getMaxCoStars()` method is provided in the Javadoc
comment immediately above the method. This question is auto-graded; your tests
will be run against multiple implementations of the `getMaxCoStars()` method,
one of which is correct and some incorrect. Your test must be able to pass
the correct implementation.   If you cannot pass the correct implementation
you will get zero (regardless of how many incorrect implementations you fail.
If you pass the correct implementation, you will get one mark, and then one
additional mark for each incorrect implementation that you fail.
 
Once you have completed your tests, commit and push your changes to GitLab.
## Q4  [20 Marks]  Data Structures

Using the incomplete template for [Q4FerrisWheel.java](src/comp1110/exam/Q4FerrisWheel.java),
complete each of the unimplemented methods. 

Your solution must implement the data structure from first principles (as was
done in lectures), avoiding use of Java's collection classes such as
`java.util.List`.  *Solutions that ignore this requirement will be penalized
accordingly.*

Use the tests provided to test your solution, and then commit and push your
changes to GitLab.

## Q5  [5 Marks]  Hashing and Equality

Using the incomplete template for [Q5Address.java](src/comp1110/exam/Q5Address.java),
complete the unimplemented methods `hash()` and `equals()`.  You must implement
`hash()` **without using Java's built-in `hashCode()` method**.

Use the tests provided to test your solution, and then commit and push your
changes to GitLab.

## Q6 [20 Marks]  Multiple Choice

For each of the following multiple-choice questions, identify the choice that
provides the best answer.  Record your choices in the file [q-6.csv](q-6.csv),
one line per question.  
 
For example, to answer "a" to question Q6.1, "d" to question Q6.2, "a" to
question Q6.4, and leave question Q6.3 unanswered, you would record the following
in `q-6.csv`:
 ````
1,a
2,d
4,a
````

Please note that you can only provide **one** answer to any multiple choice 
question.   Please chose the answer that you think is the best answer.   If
you are unsure, leave it out, as in the example above where question 3 is left
out.

Each question that is *correctly* answered **gains you 5 marks**, each question
answered *incorrectly* **loses you 1 mark**, a question left *unanswered*
**neither loses nor gains marks**. The final mark for the question is calculated
by bounding the sum of marks between 0 and 20. For example, if you answered all
questions correctly in the exam you would gain 20. If you answer 1 correctly
and 2 incorrectly you would gain 3/20.

The exam CI will check whether your answer is *correctly formatted*. Note that 
the CI won't tell you whether your answer is *correct*.   After you finish your
questions you should **commit** your `q-6.csv` file and **push** it to the
server.   You should check the CI for any error messages relating to `Q6` and
fix them if they exist.  

### Q6.1  [5 Marks]  Threads

Given the following Java class:

````
public class Thready implements Runnable {
    int x = 1;
    int y = 1;

    public void run() {
        if (x == 2) x = 0;
        else incX();
        if (y == 2) y = 0;
        else y++;
    }

    public synchronized void incX() {
        x = x + 1;
    }
}
````
  
If two separate threads are created for the same instance of `Thready`, which of
the following combinations of values for the fields `x` and `y` are not possible
after both threads have run to completion?
  
 a) `x` = 0, `y` = 3
 
 b) `x` = 2, `y` = 2
 
 c) `x` = 3, `y` = 0
 
 d) `x` = 0, `y` = 2
 
 e) `x` = 0, `y` = 0
### Q6.2  [5 Marks]  JavaFX

This question relates to the following small JavaFX program:

````
public void start(Stage stage) throws Exception {
    stage.setTitle("Square");
    Group root = new Group();
    Scene scene = new Scene(root, 300, 300);
    stage.setScene(scene);
    Rectangle r = new Rectangle(100, 100, 100, 100);
    r.setFill(Color.RED);

    stage.show();
}
````

Select the option below which best describes the behavior of the program above:

a) It shows a window titled "Square" containing a red square within a white square.

b) It shows a window titled "Stage" containing a white square.

c) It shows a window titled "Square" containing a white square.

d) It does nothing.

### Q6.3  [5 Marks]  Complexity

Consider the method `f()` below.
````
static <T extends Comparable<T>> void f(List<T> in) {
    boolean s;
    do {
        s = false;
        for (var i = 1; i < in.size() - 1; i += 2) {
            if (in.get(i).compareTo(in.get(i + 1)) > 0) {
                var temp = in.get(i);
                in.set(i, in.get(i + 1));
                in.set(i + 1, temp);
                s = true;
            }
        }
        for (var i = 0; i < in.size() - 1; i += 2) {
            if (in.get(i).compareTo(in.get(i + 1)) > 0) {
                var temp = in.get(i);
                in.set(i, in.get(i + 1));
                in.set(i + 1, temp);
                s = true;
            }
        }
    } while (s);
}
````

Assuming `in.get()` is *O(1)*, which of the following statements is correct?

a) The best-case time complexity of `f()` with `in` of size n is *O(n)*.

b) The worst-case time complexity of `f()` with `in` of size n is *O(n log n)*.

c) The worst-case time complexity of `f()` with `in` of size n is *O(2 n)*.

d) The best-case time complexity of `f()` with `in` of size n is *O(n^2)*.
### Q6.4  [5 Marks]  Grammars

This question relates to sentences (statements) in a language defined by a simple EBNF grammar.
For reference, some symbols of EBNF are as follows:

````
= defines a production rule
, concatenation
| alternation / choice
[...] optional - zero or one
{...} optional - zero or more
(...) grouping
- exception
; terminates a production rule.
````

Given the following EBNF grammars, identify the one that best defines
a grammar for accepting palindromes of the alphabet `a`, `b`, `c`, `d` 
(e.g. `aa`, `abba`, `cadac`, `babcdcbab`, etc). *Note: a palindrome is a word
that reads the same backwards and forwards.*

a)
````
pal = 'a' | 'b' | 'c' | ... | 'x' | 'y' | 'z';
````

b)
````
alph = 'a' | 'b' | 'c' | 'd';
pal = alph | ('a', [alph], 'a') | ('b', [alph], 'b') | ('c', [alph], 'c') | ('d', [alph], 'd') | seq
seq = alph, {pal}, alph;
````

c)
````
alph = 'a' | 'b' | 'c' | 'd';
pal = alph | ('a', [pal], 'a') | ('b', [pal], 'b') | ('c', [pal], 'c') | ('d', [pal], 'd');
````

d)
````
alph = 'a' | 'b' | 'c' | ... | 'x' | 'y' | 'z';
seq = { 'alph' };
pal = seq, seq;
````


*This is the end of the exam.   What follows are instructions for the practice
questions.*

---

## Practice Questions

These are instructions for what to do **before** the exam.

You should do the two practice questions below, and ensure that they work in
your environment.   You should check:
 
1. that the provided test for P1 works correctly in IntelliJ,
2. that you can successfully pass the test for P1,
3. that you can fill in the answers for P2,
4. that you can can commit and push your work,
5. that the CI says that you have passed P1 and that P2 has been checked

Note that the CI will not tell you whether the multiple choice question P2 is
correct or incorrect, it will only tell you whether it was correctly formatted.

Remember, the questions below are **practice** questions which you should attempt
during the practice *before* the exam.  The practice questions are here *only*
to help you check that your environment is working correctly.  **The practice
questions are not worth any marks.**
  
The first is an example of a programming  question.
 
### P1 [0 Marks]  Hello World

Using the incomplete template for [P1HelloWorld.java](src/comp1110/exam/P1HelloWorld.java),
complete the unimplemented main method.
 
Use the tests provided to test your solution, and then commit and push your
changes to GitLab.


### P2 [0 Marks] Sample Multiple Choice

For each of the following multiple-choice questions, identify the choice that
provides the best answer.  Record your choices in the file [p-2.csv](p-2.csv),
one line per question.  
 
For example, to answer "a" to question P2.1, "d" to question P2.2, "a" to
question P2.4, and leave question P2.3 blank, you would record the following
in `p-2.csv`:
 ````
1,a
2,d
4,a
````

These *practice* questions are worth **zero** marks.   But in the actual exam,
there will be four multiple choice questions, and each question that is
correctly answered gains you 5 marks, each question answered incorrectly loses
you 1 mark, a question left unanswered neither loses nor gains marks. The final
mark for the question in the exam is calculated by bounding the
sum of marks between 0 and 20. For example, if you answered all questions
correctly in the exam you would gain 20. If you answer 1 correctly and 2
incorrectly you would gain 3/20.

Once you have answered the questions you should **commit** your `p-2.csv` file,
and **push** it.   You should **check the exam CI** to see whether it passed
the formatting check.   If it did not, you should read the error messages
and fix up any formatting problems with your answer.  *Note that the CI won't
tell you whether your answers are correct, only whether they are correctly
formatted.*

### P2.1  [0 Marks]  Sky

Identify which of the following colors best represents the color of the sky:
             
a) blue

b) green

c) pink

d) purple

e) yellow

### P2.2  [0 Marks]  Maths

Identify which of the following numbers best represents the result of the
addition `2 + 2`:
             
a) 3

b) 2

c) 4

d) 5

e) 1

### P2.3  [0 Marks]  Animals

Identify which of the following animals is best associated with Australia:
             
a) tiger

b) elephant

c) kangaroo

d) lion

e) bear

### P2.4  [0 Marks]  Cars

Identify which of the following brands is best known as a car manufacturer:
             
a) Microsoft

b) Qantas

c) Toyota

d) Coca Cola

e) Facebook


 