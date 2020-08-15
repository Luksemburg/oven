package com.beat.oven.producer;

import com.beat.oven.producer.entity.Channel;

public interface IProducerable {
	void write(Channel channel);
}
