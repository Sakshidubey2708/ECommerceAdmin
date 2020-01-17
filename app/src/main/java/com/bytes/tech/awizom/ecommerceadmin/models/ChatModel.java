package com.bytes.tech.awizom.ecommerceadmin.models;

public class ChatModel {
    public int Chat_Id ;
    public String ClientUserId ;
    public String AdminUserId;
    public String ChatContain;
    public String MessageDate ;
    public String RoleMessageBy ;

    public int getChat_Id() {
        return Chat_Id;
    }

    public void setChat_Id(int chat_Id) {
        Chat_Id = chat_Id;
    }

    public String getClientUserId() {
        return ClientUserId;
    }

    public void setClientUserId(String clientUserId) {
        ClientUserId = clientUserId;
    }

    public String getAdminUserId() {
        return AdminUserId;
    }

    public void setAdminUserId(String adminUserId) {
        AdminUserId = adminUserId;
    }

    public String getChatContain() {
        return ChatContain;
    }

    public void setChatContain(String chatContain) {
        ChatContain = chatContain;
    }

    public String getMessageDate() {
        return MessageDate;
    }

    public void setMessageDate(String messageDate) {
        MessageDate = messageDate;
    }

    public String getRoleMessageBy() {
        return RoleMessageBy;
    }

    public void setRoleMessageBy(String roleMessageBy) {
        RoleMessageBy = roleMessageBy;
    }
}
