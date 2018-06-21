package org.zgl.session;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 作者： 白泽
 * 时间： 2017/12/1.
 * 描述：
 */
public class SessionManager {
    private static final ConcurrentHashMap<Integer, ISession> onlineSessions = new ConcurrentHashMap<>();
    public static boolean putSession(int uid,ISession session){
        boolean success = false;
        if(!onlineSessions.containsKey(uid))
            success = onlineSessions.putIfAbsent(uid,session) == null ? true : false;
        return success;
    }
    public static ISession removeSession(long playerId){
        if(!onlineSessions.containsKey(playerId)) return null;
        return onlineSessions.remove(playerId);
    }
    public static ISession getSession(int uid){
        return onlineSessions.getOrDefault(uid,null);
    }
    /**
     * 是否在线
     * @param
     * @return
     */
    public static boolean isOnlinePlayer(int uid){
        return onlineSessions.containsKey(uid);
    }

    /**
     * 获取所有在线玩家
     * @return
     */
    public static Set<Integer> onlinePlayers() {
        return Collections.unmodifiableSet(onlineSessions.keySet());
    }
    public static int onLinePlayerNum(){
        return onlineSessions == null ? 0 : onlineSessions.size();
    }
    public static Map<Integer,ISession> map(){
        return onlineSessions;
    }

}
