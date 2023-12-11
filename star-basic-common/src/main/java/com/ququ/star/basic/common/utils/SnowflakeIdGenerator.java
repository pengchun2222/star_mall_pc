package com.ququ.star.basic.common.utils;

import lombok.SneakyThrows;

/**
 * @Author: 彭淳
 * @Date: 2023/12/4 16:31
 * 雪花算法
 */
public class SnowflakeIdGenerator {
    private static final long workerId = 1;
    private static final long epoch = 1609459200000L; // 默认起始时间戳：2021-01-01 00:00:00
    private static final long workerIdBits = 5L;
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private static final long sequenceBits = 12L;
    private static final long workerIdShift = sequenceBits;
    private static final long timestampLeftShift = sequenceBits + workerIdBits;
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private static long lastTimestamp = -1L;
    private static long sequence = 0L;

    public SnowflakeIdGenerator(long workerId) {
        if (workerId < 0 || workerId > maxWorkerId) {
            throw new IllegalArgumentException("Worker ID can't be greater than " + maxWorkerId + " or less than 0");
        }
        //this.workerId = workerId;
    }

    public SnowflakeIdGenerator() {
    }

    public static synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate ID");
        }

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;

        return ((timestamp - epoch) << timestampLeftShift) |
               (workerId << workerIdShift) |
               sequence;
    }

    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private static long timeGen() {
        return System.currentTimeMillis();
    }
}
