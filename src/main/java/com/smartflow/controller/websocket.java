package com.smartflow.controller;

import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
@ServerEndpoint("/websocket")
public class websocket {
	//记录在线链接数
   private static int onlineCount=0;
   private static CopyOnWriteArraySet<websocket> webSocketSet = new CopyOnWriteArraySet<websocket>();
   private Session session;
   @OnOpen
   public void onOpen(Session session)
   {
	   this.session=session;
	   webSocketSet.add(this);
	   addOnlineCount();
	   System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
   }
   
   @OnClose
   public void onClose()
   {
	   webSocketSet.remove(this);
	   subOnlineCount();
	   System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
   }
   @OnMessage
        public void onMessage(String message, Session session) {
            System.out.println("来自客户端的消息:" + message);
            //群发消息
            for(websocket item: webSocketSet){
                try {
                    item.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                   continue;
                }
            }
        }
    
        /**
   66      * 发生错误时调用
   67      * @param session
   68      * @param error
   69      */
        @OnError
        public void onError(Session session, Throwable error){
            System.out.println("发生错误");
            error.printStackTrace();
        }
    
       /**
   77      * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
   78      * @param message
   79      * @throws IOException
   80      */
       public void sendMessage(String message) throws IOException{
            this.session.getBasicRemote().sendText(message);
            //this.session.getAsyncRemote().sendText(message);
        }
    
        public static synchronized int getOnlineCount() {
            return onlineCount;
        }
    
        public static synchronized void addOnlineCount() {
            websocket.onlineCount++;
        }
    
        public static synchronized void subOnlineCount() {
            websocket.onlineCount--;
        }
    }

