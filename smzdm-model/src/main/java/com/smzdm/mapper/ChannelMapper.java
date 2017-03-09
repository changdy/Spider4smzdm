package com.smzdm.mapper;

import com.smzdm.model.Channel;
import java.util.List;

public interface ChannelMapper {
    int insert(Channel record);

    Channel selectByPrimaryKey(Integer id);

    List<Channel> selectAll();
}