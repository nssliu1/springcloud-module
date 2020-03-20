#!/usr/bin/python
#-*-coding:utf-8-*-  
import requests
from bs4 import BeautifulSoup
import time
import json

#json1 = '{"updateTime":"","data":{"净月潭":{"smx":"1","smy":"1","smx1":"1","smy1":"1"},"劳动公园":{"smx":"","smy":"","smx1":"","smy1":""},"园林处":{"smx":"","smy":"","smx1":"","smy1":""},"客车厂":{"smx":"","smy":"","smx1":"","smy1":""},"岱山公园":{"smx":"","smy":"","smx1":"","smy1":""},"甩湾子":{"smx":"","smy":"","smx1":"","smy1":""},"经开区环卫处":{"smx":"","smy":"","smx1":"","smy1":""},"邮电学院":{"smx":"","smy":"","smx1":"","smy1":""},"食品厂":{"smx":"","smy":"","smx1":"","smy1":""},"高新区管委会":{"smx":"","smy":"","smx1":"","smy1":""}}}'
json1 = '{"updateTime":"","data":{"净月潭":{"smx":"125.46982136551368","smy":"43.776900961459461","smx1":"10438.9285496758","smy1":"-9560.40431442911"},"劳动公园":{"smx":"125.36343105195346","smy":"43.874859427847277","smx1":"1870.27777640395","smy1":"1316.39897949245"},"园林处":{"smx":"125.35766663746975","smy":"43.851044207769171","smx1":"1407.49756179485","smy1":"-1329.97452874415"},"客车厂":{"smx":"125.29608026340165","smy":"43.914607319101371","smx1":"-3540.98476560175","smy1":"5733.74401234527"},"岱山公园":{"smx":"125.21714169657696","smy":"43.852252911491206","smx1":"-9892.11616505204","smy1":"-1188.45963000024"},"甩湾子":{"smx":"125.49788899125345","smy":"43.8743879341319","smx1":"12678.0320568303","smy1":"1275.83947672386"},"经开区环卫处":{"smx":"125.41881082136014","smy":"43.858810098849048","smx1":"6323.36900617967","smy1":"-464.200504476011"},"邮电学院":{"smx":"125.28375964352988","smy":"43.840243872790154","smx1":"-4536.28883033448","smy1":"-2528.66657458563"},"食品厂":{"smx":"43.837856473833","smy":"43.837856473833","smx1":"7754.33017116408","smy1":"-2790.97222707746"},"高新区管委会":{"smx":"125.25245905568956","smy":"43.786566503379653","smx1":"-7059.98915854408","smy1":"-8490.85309759798"}}}'

jsons1 = json.loads(json1)

res = requests.get('http://www.pm25s.com/changchun.html')
res.encoding='utf-8'
soup = BeautifulSoup(res.text,'html.parser')

aqis = soup.select('.pm25')[0].find_all('div')
dates = soup.select('#content .date')[0]
h = dates.text.split('日')[1].split('时')[0]

he = time.strftime("%Y-%m-%d", time.localtime()) + ' ' + h + ':00:00'
#放入时间
jsons1["updateTime"] = he


for index in range(len(aqis)):
    if index==0:
        continue
    one = aqis[index]
    #print(iter(one.children))
    prev = one.next
    aqiPointName = prev
    
    #print(aqiPointName.text)
    aqiPointNameText = aqiPointName.text
    aqi = aqiPointName.next_sibling
    jsons1["data"][aqiPointNameText]["position_name"] = aqiPointNameText
    jsons1["data"][aqiPointNameText]["aqi"] = aqi.text;
    #print(aqi.text)
    
    pm25 = aqi.next_sibling
    jsons1["data"][aqiPointNameText]["pm25"] = pm25.text;
    #print(pm25.text)
    
    pm10 = pm25.next_sibling
    jsons1["data"][aqiPointNameText]["pm10"] = pm10.text;
    #print(pm10.text)
    co = pm10.next_sibling
    jsons1["data"][aqiPointNameText]["co"] = co.text;
    #print(co.text)
    NO2 = co.next_sibling
    jsons1["data"][aqiPointNameText]["no2"] = NO2.text;
    #print(NO2.text)
    SO2=NO2.next_sibling
    jsons1["data"][aqiPointNameText]["so2"] = SO2.text;
    #print(SO2.text)
    O31h=SO2.next_sibling
    jsons1["data"][aqiPointNameText]["o31h"] = O31h.text;
    #print(O31h.text)
    O38h=O31h.next_sibling
    jsons1["data"][aqiPointNameText]["o38h"] = O38h.text;
    #print(O38h.text)
    level=O38h.next_sibling
    jsons1["data"][aqiPointNameText]["level"] = level.text;
    #print(level.text)
  
print(jsons1)
