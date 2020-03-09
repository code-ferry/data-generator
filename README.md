# 概述
在大数据的学习与工作中，常常需要产生大量的实验或者测试数据。数据发生器（data-generator）的作用就是为了方便生成各种类型的数据。  
工具可以快速CSV结构的文本文件：
* 可以指定文件的字段分隔符，列分隔符。
* 文件名，文件路径支持变量。
* 可以指定文件生成的并行度，生成记录数，生成时间的时长。
* 支持字符型，数值型，日期型，业务型数据。
* ......

# 快速入门
## 编译打包
编译代码并打包，得到文件data-generator-all.tar.gz。
```
mvn -Dmaven.test.skip=true clean package
```
## 启动
```
# 解压程序包
tar -zxvf data-generator-all.tar.gz
# 进入启动目录
cd data-generator/bin
# 运行启动脚本
```

## 运行命令访问restful接口
运行curl向服务请求生成数据。根据实际情况，设置fileName，filePath。其中，${XXX}的内容为变量，可参见“变量”章节
```
curl --location --request POST 'http://192.168.128.201:8787/dg/file' \
--header 'Content-Type: application/json' \
--data '{
	"fieldSeparator":",",
	"fileCharset":"utf-8",
	"fileName":"testfile-${HHmmss}.txt",
	"filePath":"/home/bduser/${MMDD}",
	"isAppend":true,
	"lineSeparator":"\r\n",
	"parallelDegree":1,
	"recordCount":10,
	"runningDuration": 2,
	"runningTimes":1,
	"fieldInfos":[
		{
			"type":"long",
			"minValue": 20,
			"maxValue": 10000
		},
		{
			"type":"double",
			"minValue": 5,
			"maxValue": 50,
			"decimalLength": 3
		},
		{
			"type":"sequence",
			"stepValue": "2",
			"minValue": 20,
			"maxValue": 10000
		},
		{
			"type":"date",
			"startTime": "2019-11-01 00:00:00",
			"endTime": "2019-11-06 00:00:00",
			"dateFormat": "yyyy/MM/dd hh:mm:ss"
		},
		{
			"type":"utc",
			"startTime": "2019-11-01 00:00:00",
			"endTime": "2019-11-06 00:00:00"
		},
		{
			"type":"ip"
		},
		{
			"type":"telephone"
		}
	]
}'
```

生成日志信息
```
11-09 11:27:20.287 [INFO ] [com.newland.dg.work.WriteMainWorker     :50  ] [MainWorker              ] - ☆☆☆ 当前是第1次的写入文件
11-09 11:27:20.661 [INFO ] [com.newland.dg.work.WriteWorker         :71  ] [MainWorker              ] - ☆☆☆ 写入文件'/home/bduser/0309/testfile-112720.txt'开始。
11-09 11:27:20.667 [INFO ] [com.newland.dg.work.WriteWorker         :94  ] [MainWorker              ] - ☆☆☆ 写入文件'/home/bduser/0309/testfile-112720.txt'完成，用时'0'秒。
11-09 11:27:20.667 [INFO ] [com.newland.dg.work.WriteMainWorker     :45  ] [MainWorker              ] - ☆☆☆ 所有文件创建完成!
```
生成文件
```
[bduser@host201 1109]$ ll
total 4
-rw-rw-r-- 1 bduser bduser 758 Mar  9 11:27 testfile-112720.txt
```
文件内容示例
```
[bduser@host201 0309]$ cat testfile-112720.txt
430,5.512,22,2019/11/02 10:04:53,1572557013049,171.9.137.90,13510529949
1588,17.788,24,2019/11/02 12:45:52,1572588946432,182.87.71.195,13423706510
6115,32.055,26,2019/11/05 09:38:19,1572543313337,182.91.36.89,13890479380
6532,27.004,28,2019/11/01 03:45:52,1572717355601,121.76.204.173,15366675651
1926,26.543,30,2019/11/02 11:14:13,1572684546953,123.233.57.169,15521023054
1542,13.235,32,2019/11/04 04:38:16,1572680148498,139.211.99.62,15367907363
6408,5.442,34,2019/11/05 09:11:55,1572936373933,121.76.164.68,13532200001
5357,23.128,36,2019/11/05 07:16:41,1572837446451,222.34.114.168,13905573566
690,30.361,38,2019/11/01 03:43:52,1572713506221,139.203.30.147,13112694812
1128,17.205,40,2019/11/01 10:08:43,1572916521747,121.76.202.88,18933535628
```

# 基本属性

基本属性值|说明|默认值|必填|备注
|:----:|:----|:----:|:----:|:----|
|recordCount|单个文件的记录数|1000||
|filePath|文件路径，支持变量| |√|变量详见"变量"章节|
|fileName|文件名，支持变量| |√|变量详见"变量"章节|
|fileCharset|文件编码|UTF-8| |
|fieldSeparator|字段分隔符|,| |可以直接用字符，也可以用0x??的16进制数的方式表示不可见字符
|lineSeparator|行分隔符|\r\n| |
|isAppend|是否追加|false| |
|runningTimes|"运行次数，可以是大于0的正数也可以0或者负数表示"|1| |runningTimes优先级高于runningDuration。当runningTimes为0或者负数，runningDuration才生效。
|runningDuration|运行时长，分钟做为单位。|0| |
|parallelDegree|并行度|1| |"当parallelDegree大于1时，文件名后会加上"-id"如：filename-1.txt"
|fieldInfos|字段信息| | |详见：字段数值型、字段字符型等sheet。

# 字段类型说明
## 字段数值型
|作用|类型说明|参数|默认值|必填|备注
|:----:|:----:|:----:|:----:|:----:|:----
|产生数据序列|sequence|minValue|1| |最小值, 只支持整型
| | |maxValue|100| |最大值, 只支持整型
| | |stepValue|2| |步长, 只支持整型
|产生布尔值|boolean|useNumber|false| |是否用数字表示布尔值. 数字用0,1表示; 选true时表示用文本, 用true,false表示. 
| | |caseType|lower| |文本表示时的大小写类型，包括lower,upper,capital，分别表示小写,大写,首字母大写
|产生整型数|long|minValue| |√|最小值
| | |maxValue| |√|最大值
| | |fixedLength| | |需要固定长度时填写长度，但是要大等于最大值的长度
| | |paddingPosition|l| |l表示左填充；r表示是右填充
| | |paddingChar|空格| |填充的字符
|产生浮点数|double|minValue| |√|最小值
| | |maxValue| |√|最大值
| | |decimalLength|2| |小数点的位数

## 字段字符型
|作用|类型说明|参数|默认值|必填|备注
|:----:|:----:|:----:|:----:|:----:|:----
|产生空字符串|empty| | | | 
|产生字符串|string|fixedLength|5| |随机字符串的长度
| | |minLength| |√|随机字符串最小长度
| | |maxLength| |√|随机字符串最大长度，当有填该值时，固定长度值失效
| | |preDefineChars| |√|预定义字符集合，类型用逗号分隔。1. 小写字母；2. 大写字母；3. 数值
| | |userDefineChars| | |在预定义字符串的基础上，加上部分自定义字符。如：$#_()
|产生字符串
(基于特定字符串数组)|strings|userDefineChars| |√|自定义的字符集，不同于string中的userDefineChars，其是一个数组。比如：aa,bb,cc,dd

## 字段日期型
|作用|类型说明|参数|默认值|必填|备注
|:----:|:----:|:----:|:----:|:----:|:----
|产生日期文本|date|startTime|2019-01-01 00:00:00| |开始时间，格式只支持yyyy-MM-dd HH:mm:ss的形式
| | |endTime|2019-12-31 23:59:59| |结束时间，格式只支持yyyy-MM-dd HH:mm:ss的形式
| | |dateFormat|yyyy-MM-dd hh:mm:ss| |输出时间的格式。
|产生UTC时间(单位毫秒)|utc|startTime|2019-01-01 00:00:00| |开始时间，格式只支持yyyy-MM-dd HH:mm:ss的形式
| | |endTime|2019-12-31 23:59:59| |结束时间，格式只支持yyyy-MM-dd HH:mm:ss的形式
|产生UTC1000时间(单位秒)|utc1000|startTime|2019-01-01 00:00:00| |开始时间，格式只支持yyyy-MM-dd HH:mm:ss的形式
| | |endTime|2019-12-31 23:59:59| |结束时间，格式只支持yyyy-MM-dd HH:mm:ss的形式
|产生当前时间|now|dateFormat|yyyy-MM-dd hh:mm:ss.SSS| |输出时间的格式。
|产生当前utc|nowutc| | | |

## 字段业务型
|作用|类型说明|参数|默认值|必填|备注
|:----:|:----:|:----:|:----:|:----:|:----
|产生手机号码|telephone| | | | 
|产生Ip地址|ip| | | | 
|产生Ipv6地址|ipv6| | | | 
|产生身份证号码|idcard| | | | 
|产生UUID|uuid| | | | 
|产生中文名字|chinesename| | | | 
|产生中文路名|loadname| | | | 

# 变量
通过格式${XXXX}，其中XXXX是下面的变量。变量通常应用在文件名与文件夹上。

| 变量名 | 说明 | 备注 |
| :----: | :----: | :----: |
| YYYYMM
| YYYYMMDD
| YYYYMMDDHH 
| YYYYMMDDHHmm
| YYYYMMDDHHmmss
| MMDDHH
| DDHHmm 
| MMDD	
| DDHH	
| HHmmss	
| HHmm	
| mmss	
| YYYY | 年
| MM   | 月
| DD   | 日
| HH   | 小时
| mm   | 分钟
| ss   | 秒

# 完整示例
该示例包括比较完整的字段类型。
```
curl --location --request POST 'http://localhost:8787/dg/file' \
--header 'Content-Type: application/json' \
--data '{
	"fieldSeparator":",",
	"fileCharset":"utf-8",
	"fileName":"testfile-${HHmmss}.txt",
	"filePath":"d:\\${MMDD}",
	"isAppend":true,
	"lineSeparator":"\r\n",
	"parallelDegree":2,
	"recordCount":10,
	"runningDuration": 2,
	"runningTimes":1,
	"fieldInfos":[
		{
			"type":"string",
			"fixedLength":5,
			"preDefineChars":[
				"1"
			]
		},
		{
			"type":"strings",
			"userDefineString":"aa,bb,cc,dd,ee,ff,gg,ii"
		},
		{
			"type":"string",
			"fixedLength":10,
			"preDefineChars": ["2", "3"]
		},
		{
			"type":"empty"
		},
		{
			"type":"long",
			"minValue": 20,
			"maxValue": 10000
		},
		{
			"type":"double",
			"minValue": 5,
			"maxValue": 50,
			"decimalLength": 3
		},
		{
			"type":"sequence",
			"stepValue": "2",
			"minValue": 20,
			"maxValue": 10000
		},
		{
			"type":"boolean",
			"caseType":"upper"
		},
		{
			"type":"boolean",
			"useNumber": true
		},
		{
			"type":"date",
			"startTime": "2019-11-01 00:00:00",
			"endTime": "2019-11-06 00:00:00",
			"dateFormat": "yyyy/MM/dd hh:mm:ss"
		},
		{
			"type":"utc",
			"startTime": "2019-11-01 00:00:00",
			"endTime": "2019-11-06 00:00:00"
		},
		{
			"type":"utc1000",
			"startTime": "2019-11-01 00:00:00",
			"endTime": "2019-11-06 00:00:00"
		},
		{
			"type":"ip"
		},
		{
			"type":"uuid"
		},
		{
			"type":"telephone"
		},
		{
			"type":"idcard"
		},
		{
			"type":"chinesename"
		},
		{
			"type":"loadname"
		}
	]
}
'
```
