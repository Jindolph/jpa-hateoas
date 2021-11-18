# jpa-hateoas

To be honest, I felt any frustration with Spring Rest Repositories to create.
This is very convenient way to make JPA project. 
Actually, it was possible to make REST service without controller.
But, under the surface, i didn't understand how it works.

This HATEOAS helped me to understand better with those ways. thanks.


==========================================================================
                       Second Commit
==========================================================================

I added some 'custom links' and checked if it works correctly.

You can check src/main/java ... /Custom/*


start maven run.

and check with cUrl

curl localhost:8080/articles | json_pp

You can see Json below.

<pre>
<code>
{
   "_embedded" : {
      "articleList" : [
         {
            "_links" : {
               "after" : {
                  "href" : "http://localhost:8080/articles/2"
               },
               "title0" : {
                  "href" : "http://localhost:8080/articles/1"
               }
            },
            "author" : "author0",
            "body" : "body0",
            "id" : 1,
            "title" : "title0"
         },
         {
            "_links" : {
               "after" : {
                  "href" : "http://localhost:8080/articles/3"
               },
               "before" : {
                  "href" : "http://localhost:8080/articles/1"
               },
               "title1" : {
                  "href" : "http://localhost:8080/articles/2"
               }
            },
            "author" : "author1",
            "body" : "body1",
            "id" : 2,
            "title" : "title1"
         },
         {
            "_links" : {
               "after" : {
                  "href" : "http://localhost:8080/articles/4"
               },
               "before" : {
                  "href" : "http://localhost:8080/articles/2"
               },
               "title2" : {
                  "href" : "http://localhost:8080/articles/3"
               }
            },
            "author" : "author2",
            "body" : "body2",
            "id" : 3,
            "title" : "title2"
         },
         {
            "_links" : {
               "after" : {
                  "href" : "http://localhost:8080/articles/5"
               },
               "before" : {
                  "href" : "http://localhost:8080/articles/3"
               },
               "title3" : {
                  "href" : "http://localhost:8080/articles/4"
               }
            },
            "author" : "author3",
            "body" : "body3",
            "id" : 4,
            "title" : "title3"
         },
         {
            "_links" : {
               "befre" : {
                  "href" : "http://localhost:8080/articles/4"
               },
               "title4" : {
                  "href" : "http://localhost:8080/articles/5"
               }
            },
            "author" : "author4",
            "body" : "body4",
            "id" : 5,
            "title" : "title4"
         }
      ]
   },
   "_links" : {
      "self" : {
         "href" : "http://localhost:8080/articles"
      }
   }
}

</code>
</pre>


Can you see the beautiful useful links? I got it well.

when the requested article id is 1(the first one), only returned the 'after links'.
In the same context, With the last article id, only 'before links'.

thanks.
