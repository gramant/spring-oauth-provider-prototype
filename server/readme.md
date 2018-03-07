## get token
```curl -vu web:web-secret localhost:8081/oauth/token -d grant_type=password -d username=a@a.ru -d password=abc```

## check token
```curl -X POST -vu web:web-secret localhost:8081/oauth/check_token -d token=adc67718-85f7-4eb2-9018-60e92d389b65```