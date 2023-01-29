# Сервис мониторинга

![example workflow](https://github.com/muravevartem/MonitoringService/actions/workflows/ci.yml/badge.svg)

### Получение пути перемещения устройства

```http request
GET /api/v1/track?t={transportId}
```

### Последнее местоположение устройства

```http request
GET /api/v1/track/last
```

### Фиксация местоположения устройства

```http request
POST /api/v1/track
Content-Type: application/json; charset=utf-8

{
   "tId": /*transportId*/,
   "lat": /*latitude*/,
   "lng": /*longitude*/
}
```