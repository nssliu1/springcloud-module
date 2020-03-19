#!/usr/bin/python
#-*-coding:utf-8-*-  
import requests
from bs4 import BeautifulSoup
import time
import json

json1 = '{"updateTime":"","data":{"净月潭":{"smx":"1","smy":"1","smx1":"1","smy1":"1"},"劳动公园":{"smx":"","smy":"","smx1":"","smy1":""},"园林处":{"smx":"","smy":"","smx1":"","smy1":""},"客车厂":{"smx":"","smy":"","smx1":"","smy1":""},"岱山公园":{"smx":"","smy":"","smx1":"","smy1":""},"甩湾子":{"smx":"","smy":"","smx1":"","smy1":""},"经开区环卫处":{"smx":"","smy":"","smx1":"","smy1":""},"邮电学院":{"smx":"","smy":"","smx1":"","smy1":""},"食品厂":{"smx":"","smy":"","smx1":"","smy1":""},"高新区管委会":{"smx":"","smy":"","smx1":"","smy1":""}}}'
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
