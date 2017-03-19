package com.smzdm.handler;

import com.alibaba.fastjson.JSONObject;
import com.smzdm.mapper.ChannelMapper;
import com.smzdm.model.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Changdy on 2017/3/6.
 */
@Service
public class ChannelHandler {
    private final ChannelMapper channelMapper;

    private List<Channel> channelList = new ArrayList<>();

    @Autowired
    public ChannelHandler(ChannelMapper channelMapper) {
        this.channelMapper = channelMapper;
    }

    public List<Channel> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }

    public void initList() {
        channelList = channelMapper.selectAll();
    }

    public Integer getChannelId(JSONObject jsonContent) {
        String articleChannel = jsonContent.getString("article_channel").trim();
        int size = channelList.size();
        for (int i = 0; i < size; i++) {
            if (articleChannel.equals(channelList.get(i).getTitle())) {
                return i + 1;
            }
        }
        Channel channel = new Channel(size + 1, jsonContent.getString("article_channel_url").trim(), articleChannel);
        channelMapper.insert(channel);
        channelList.add(channel);
        return size + 2;
    }
}
