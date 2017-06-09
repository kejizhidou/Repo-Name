package com.fq.miblog.client.domain;

/**
 * @author jifang
 * @since 16/7/7 下午4:01.
 */
public class Relation {

    private long from;

    private long to;

    public Relation(long from, long to) {
        this.from = from;
        this.to = to;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }
}
