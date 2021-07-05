# News-Sample-App-Kotlin

Sample News app for showing list of news with images.

1. Go to https://newsapi.org/ and register and get the API Key and add in NewsService.kt under retroService package.
2. App uses retrofit to download the newslist.
3. App uses Recyclerview to show the list of news.
4. Dao class provides the news after parsing to the adapter class.
5. To open the news article, app uses CustomTabIntent and opens in app tab using browser dependancy.

