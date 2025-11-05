# TODOs
## TODO 1 
```kotlin
 apiHelper.login(email, password).map {
    AuthenticationProvider.setToken(it.token)
    it.toDomain()
}
```
## TODO 1
```kotlin
apiHelper.signup(username, email, password).map {
    AuthenticationProvider.setToken(it.token)
    it.toDomain()
}
```

