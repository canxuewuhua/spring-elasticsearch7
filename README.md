2023 05 13 es first commit

全文检索

es7 默认jdk的版本为11 中文分词器   kibana客户端

安装es不允许使用root用户 需要使用普通用户
useradd chenyn
passwd chenyn

 使用普通用户启动es客户端 kibana客户端

1 创建索引
PUT /索引名   ======> PUT /products

2 创建索引 进行索引分片配置
PUT /products
{ 
   "setting":{
        "number_of_shards": 1, # 指定主分片的数量
        "number_of_replicas": 0 # 指定副本分片的数量
   }
}

3 查询索引
GET /_cat/indices?v
4 删除索引
DELETE /products
DELETE /* `*代表通配符,代表所有索引`

5 .创建索引&&映射
PUT /products
{
    "settings": {
    "number_of_shards": 1,
    "number_of_replicas": 0
    },
    "mappings": {
        "properties": {
            "title":{
            "type": "keyword"
            },
            "price":{
            "type": "double"
            },
            "created_at":{
            "type": "date"
            },
            "description":{
            "type": "text"
            }
       }
    }
}
说明: ES中⽀持字段类型⾮常丰富，如：text、keyword、integer、 long、ip 等。

查看某个索引的映射
GET /products/_mapping

添加文档
POST /products/_doc/1 #指定⽂档id
    {
        "title":"iphone13",
        "price":8999.99,
        "created_at":"2021-09-15",
        "description":"iPhone 13屏幕采⽤6.1英⼨OLED屏幕。" 
    }

查询文档
GET /products/_doc/1
删除文档
DELETE /products/_doc/1
更新⽂档
PUT /products/_doc/sjfYnXwBVVbJgt24PlVU
{
   "title":"iphon15"
}
说明: 这种更新⽅式是先删除原始⽂档,在将更新⽂档以新的内容插⼊。

es是全文检索
本文的elasticsearch安装时通过docker安装


elasticsearch的安装
docker logs -f 容器id
docker pull elasticsearch:7.14.2
docker pull kibana:7.14.2

ik分词器
wget https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.14.2/elasticsearch-analysis-ik-7.14.2.zip

启动elasticsearch
docker run -p 9200:9200 -p 9300:9300 -d  -e "discovery.type=single-node" --restart=always  -v /root/es/esplugins:/usr/share/elasticsearch/plugins -v /root/es/data:/usr/share/elasticsearch/data --name es  elasticsearch:7.14.2

启动kibana
docker run -d --name kibana -e ELASTICSEARCH_URL=http://172.16.244.128:9200 -p 5601:5601 kibana:7.14.2
需进入容器内修改 vi kibana.yml 将ip地址改为172.16.244.128
elasticsearch.hosts: [ "http://172.16.244.128:9200" ]  注意：重新启动后 注意是不是ip地址是这个
      - docker run -d -v /root/kibana.yml:/usr/share/kibana/config/kibana.yml  --name kibana -p 5601:5601 kibana:7.14.0
注意：es的安装 elasticsearch和kibana的版本要一致 目前7.14.2这个版本对于mac的64位是可以适配的

elasticsearch客户端RestHighLevelClient的API
相关API学习网址：https://blog.csdn.net/aa674715201/article/details/128803525

172.16.244.128为本机安装虚拟机的ip地址
