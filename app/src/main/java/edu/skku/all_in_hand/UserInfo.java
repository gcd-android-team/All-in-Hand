package edu.skku.all_in_hand;

import java.util.HashMap;
import java.util.Map;

/*
데이터베이스에서 유저 정보 가져올 때
 */

public class UserInfo {
    public String id;
    public String pw;

    public UserInfo(){

    }

    public UserInfo(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("pw", pw);
        return result;
    }
}
