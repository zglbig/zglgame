package org.zgl.message;

/**
 * @作者： big
 * @创建时间： 2018/6/29
 * @文件描述：
 */
public class GateIoMessage implements IoMessage {
    private short cmd;//注册 服务器间交互 客户端请求
    private short gameId;
    private byte[] message;

    public GateIoMessage() {
    }

    public GateIoMessage(short cmd, short gameId, byte[] message) {
        this.cmd = cmd;
        this.gameId = gameId;
        this.message = message;
    }

    public short getCmd() {
        return cmd;
    }

    public void setCmd(short cmd) {
        this.cmd = cmd;
    }

    public short getGameId() {
        return gameId;
    }

    public void setGameId(short gameId) {
        this.gameId = gameId;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }
}
