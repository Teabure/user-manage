## 数据库表结构

1. 用户表`t_user`

```sqlite
CREATE TABLE "t_user"(
	"id"            INTEGER NOT NULL,
    "name"          TEXT,
    "age"           INTEGER,
    "sex"           TEXT,
    "telephone"     TEXT,
    "province_code" TEXT,
    "province_name" TEXT,
    "city_code"     TEXT,
    "city_name"     TEXT,
    "area_code"     TEXT,
    "area_name"     TEXT,
    "address"       TEXT,
    "deleted"       integer default 0,
    PRIMARY KEY ("id")
);
```

2. 行政区域表`t_dict_district`

```sqlite
CREATE TABLE "t_dict_district" (
    "id" INTEGER NOT NULL,
    "parent" TEXT,
    "code" TEXT NOT NULL,
    "name" TEXT,
    PRIMARY KEY ("id")
);
```

3. 日志表`t_undo_log`

```sqlite
CREATE TABLE "t_undo_log"(
    "id"          INTEGER NOT NULL,
    "user_host"   TEXT,
    "row_id"      INTEGER,
    "action"      INTEGER,
    "user"        TEXT,
    "create_time" INTEGER,
    PRIMARY KEY ("id")
);
```

## 接口与数据模型

Base URLs:

* <a href="http://127.0.0.1:8080">开发环境: http://127.0.0.1:8080</a>

### 用户接口

#### POST 分页查询

POST /user/list

> Body 请求参数

```json
{
  "query": "string",
  "current": 0,
  "size": 0
}
```

##### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|body|body|[map](#schemamap)| 否 | 分页参数|none|

> 返回示例

> 200 Response

```json
{
  "records": [
    {
      "id": "string",
      "name": "string",
      "age": 0,
      "sex": "string",
      "telephone": "string",
      "provinceCode": "string",
      "provinceName": "string",
      "cityCode": "string",
      "cityName": "string",
      "areaCode": "string",
      "areaName": "string",
      "address": "string",
      "deleted": 0
    }
  ],
  "total": 0,
  "size": 0,
  "current": 0,
  "orders": [
    "string"
  ],
  "optimizeCountSql": true,
  "searchCount": true,
  "countId": null,
  "maxLimit": null,
  "pages": 0
}
```

##### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

##### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» records|[object]|true|none||none|
|»» id|string|true|none||none|
|»» name|string|true|none||none|
|»» age|integer|true|none||none|
|»» sex|string|true|none||none|
|»» telephone|string|true|none||none|
|»» provinceCode|string|true|none||none|
|»» provinceName|string|true|none||none|
|»» cityCode|string|true|none||none|
|»» cityName|string|true|none||none|
|»» areaCode|string|true|none||none|
|»» areaName|string|true|none||none|
|»» address|string|true|none||none|
|»» deleted|integer|true|none||none|
|» total|integer|true|none||none|
|» size|integer|true|none||none|
|» current|integer|true|none||none|
|» orders|[string]|true|none||none|
|» optimizeCountSql|boolean|true|none||none|
|» searchCount|boolean|true|none||none|
|» countId|null|true|none||none|
|» maxLimit|null|true|none||none|
|» pages|integer|true|none||none|

#### GET 获取用户信息

GET /user/{id}

##### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|id|path|integer| 是 ||none|

> 返回示例

> 200 Response

```json
{
  "id": 0,
  "name": "string",
  "age": 0,
  "sex": "string",
  "telephone": "string",
  "provinceCode": "string",
  "provinceName": "string",
  "cityCode": "string",
  "cityName": "string",
  "areaCode": "string",
  "areaName": "string",
  "address": "string",
  "deleted": 0
}
```

##### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|[User](#schemauser)|

#### DELETE 删除用户

DELETE /user/{id}

##### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|id|path|integer| 是 ||none|

> 返回示例

> 200 Response

```json
true
```

##### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|boolean|

#### POST 添加用户

POST /user

> Body 请求参数

```json
{
  "id": 0,
  "name": "string",
  "age": 0,
  "sex": "string",
  "telephone": "string",
  "provinceCode": "string",
  "provinceName": "string",
  "cityCode": "string",
  "cityName": "string",
  "areaCode": "string",
  "areaName": "string",
  "address": "string",
  "deleted": 0
}
```

##### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|body|body|[User](#schemauser)| 否 | 用户类|none|

> 返回示例

> 200 Response

```json
true
```

##### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|boolean|

#### PUT 修改用户

PUT /user

> Body 请求参数

```json
{
  "id": 0,
  "name": "string",
  "age": 0,
  "sex": "string",
  "telephone": "string",
  "provinceCode": "string",
  "provinceName": "string",
  "cityCode": "string",
  "cityName": "string",
  "areaCode": "string",
  "areaName": "string",
  "address": "string",
  "deleted": 0
}
```

##### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|body|body|[User](#schemauser)| 否 | 用户类|none|

> 返回示例

> 200 Response

```json
true
```

##### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|boolean|

#### POST 批量删除用户

POST /user/remove/batch

> Body 请求参数

```json
[
  "string"
]
```

##### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|body|body|array[string]| 否 ||none|

> 返回示例

> 200 Response

```json
true
```

##### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|boolean|

### 区域代码接口

#### GET 通过区域代码获取其子区域

GET /district/children/{code}

##### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|code|path|string| 是 ||none|

> 返回示例

> 200 Response

```json
[
  {
    "id": 0,
    "parent": "string",
    "code": "string",
    "name": "string"
  }
]
```

##### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

##### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|*anonymous*|[[DictDistrict](#schemadictdistrict)]|false|none||none|
|» 详细区域代码|[DictDistrict](#schemadictdistrict)|false|none|详细区域代码|none|
|»» id|integer|true|none||none|
|»» parent|string|true|none||none|
|»» code|string|true|none||none|
|»» name|string|true|none||none|

#### GET 获取全部区域代码

GET /district/all

> 返回示例

> 200 Response

```json
[
  {
    "code": "string",
    "name": "string",
    "children": [
      {
        "code": "string",
        "name": "string",
        "children": [
          {
            "code": "string",
            "name": "string",
            "children": [
              null
            ]
          }
        ]
      }
    ]
  }
]
```

##### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

##### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|*anonymous*|[[DistrictVo](#schemadistrictvo)]|false|none||none|
|» 区域代码|[DistrictVo](#schemadistrictvo)|false|none|区域代码|none|
|»» code|string|true|none||none|
|»» name|string|true|none||none|
|»» children|[[DistrictVo](#schemadistrictvo)]|true|none||none|
|»»» 区域代码|[DistrictVo](#schemadistrictvo)|false|none|区域代码|none|
|»»»» code|string|true|none||none|
|»»»» name|string|true|none||none|
|»»»» children|[[DistrictVo](#schemadistrictvo)]|true|none||none|

### 撤销接口

#### GET 撤销

GET /undo

> 返回示例

> 200 Response

```json
true
```

##### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|boolean|

### 数据模型

<h2 id="tocS_DictDistrict">DictDistrict</h2>

<a id="schemadictdistrict"></a>
<a id="schema_DictDistrict"></a>
<a id="tocSdictdistrict"></a>
<a id="tocsdictdistrict"></a>

```json
{
  "id": 0,
  "parent": "string",
  "code": "string",
  "name": "string"
}

```

详细区域代码

##### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|id|integer|true|none||none|
|parent|string|true|none||none|
|code|string|true|none||none|
|name|string|true|none||none|

<h2 id="tocS_map">map</h2>

<a id="schemamap"></a>
<a id="schema_map"></a>
<a id="tocSmap"></a>
<a id="tocsmap"></a>

```json
{
  "query": "string",
  "current": 0,
  "size": 0
}

```

分页参数

##### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|query|string|true|none||none|
|current|integer|true|none||none|
|size|integer|true|none||none|

<h2 id="tocS_DistrictVo">DistrictVo</h2>

<a id="schemadistrictvo"></a>
<a id="schema_DistrictVo"></a>
<a id="tocSdistrictvo"></a>
<a id="tocsdistrictvo"></a>

```json
{
  "code": "string",
  "name": "string",
  "children": [
    {
      "code": "string",
      "name": "string",
      "children": [
        {
          "code": "string",
          "name": "string",
          "children": [
            {}
          ]
        }
      ]
    }
  ]
}

```

区域代码

##### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|string|true|none||none|
|name|string|true|none||none|
|children|[[DistrictVo](#schemadistrictvo)]|true|none||none|

<h2 id="tocS_User">User</h2>

<a id="schemauser"></a>
<a id="schema_User"></a>
<a id="tocSuser"></a>
<a id="tocsuser"></a>

```json
{
  "id": 0,
  "name": "string",
  "age": 0,
  "sex": "string",
  "telephone": "string",
  "provinceCode": "string",
  "provinceName": "string",
  "cityCode": "string",
  "cityName": "string",
  "areaCode": "string",
  "areaName": "string",
  "address": "string",
  "deleted": 0
}

```

用户类

##### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|id|integer|true|none||none|
|name|string|true|none||none|
|age|integer|true|none||none|
|sex|string|true|none||none|
|telephone|string|true|none||none|
|provinceCode|string|true|none||none|
|provinceName|string|true|none||none|
|cityCode|string|true|none||none|
|cityName|string|true|none||none|
|areaCode|string|true|none||none|
|areaName|string|true|none||none|
|address|string|true|none||none|
|deleted|integer|true|none||none|

