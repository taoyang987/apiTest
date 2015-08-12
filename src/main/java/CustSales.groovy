import groovyx.net.http.HTTPBuilder
import net.sf.json.groovy.JsonSlurper
import static groovyx.net.http.ContentType.URLENC
import static groovyx.net.http.Method.POST

class CustSales {
    public static void main(args) {
        def paramValue = '{"actId":"","bactId":"","busMobile":"13591159520","categoryId":"","channelId":"1003","custId":"NF5066235","delivery":[],"empId":"105136","exFamoney":"","exFapri":"","hquantity":"","orderFlag":"Y","orderInfos":"109526&1&170&100","orgId":"415","posId":"41211","quantity":"","summer":[{"actId":109526,"packageSize":1,"vividList":[{"categoryId":170,"quantityBottle":0,"quantityBox":100,"type":1}]}]}'
        def tokenValue = MD5Utils.encode(paramValue, MD5Utils.MD5_KEY)
        def result=0

        def http = new HTTPBuilder('http://vendtest.nfsq.com.cn:8888')
        http.request(groovyx.net.http.Method.POST, groovyx.net.http.ContentType.URLENC) {
            req ->
                headers.Accept = 'application/json'
                headers.'User-Agent' = 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.125 Safari/537.36'
                headers.'http.accept_language'='zh-CN,zh;q=0.8'

                uri.path = '/nfsq_xs/terminal/promo/order.json'
                body = [
                        token  : tokenValue,
                        param  : paramValue,
//                        version: 100
                ]
                response.success = { resp, json ->
                    println json
                    println json.code
                    println json.message
                    println json.success
                    def objJson = jsonSlurper.parseText(obj.toString())
                    println objJson
                    result=objJson.code
                    println result
//
//                    def jsonSlurper = new JsonSlurper()
//                    def object = jsonSlurper.parseText(json)
                }


        }
    }
}
