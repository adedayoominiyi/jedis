package redis.clients.jedis.commands;

import java.util.Map;
import java.util.Map.Entry;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.args.ListDirection;
import redis.clients.jedis.args.UnblockType;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.params.GetExParams;
import redis.clients.jedis.params.MigrateParams;
import redis.clients.jedis.params.ClientKillParams;
import redis.clients.jedis.params.RestoreParams;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.params.XAddParams;
import redis.clients.jedis.params.XAutoClaimParams;
import redis.clients.jedis.params.XClaimParams;
import redis.clients.jedis.params.XPendingParams;
import redis.clients.jedis.params.XTrimParams;
import redis.clients.jedis.params.ZAddParams;
import redis.clients.jedis.params.ZIncrByParams;
import redis.clients.jedis.params.LPosParams;
import redis.clients.jedis.params.XReadGroupParams;
import redis.clients.jedis.params.XReadParams;

public interface Commands {

  void copy(String srcKey, String dstKey, int db, boolean replace);

  void copy(String srcKey, String dstKey, boolean replace);

  void ping(String message);

  void set(String key, String value);

  void set(String key, String value, SetParams params);

  void get(String key);

  void getDel(String key);

  void getEx(String key, GetExParams params);

  void exists(String... keys);

  void del(String... keys);

  void unlink(String... keys);

  void type(String key);

  void keys(String pattern);

  void rename(String oldkey, String newkey);

  void renamenx(String oldkey, String newkey);

  /**
   * @deprecated Use {@link #expire(java.lang.String, long)}.
   */
  @Deprecated
  default void expire(String key, int seconds) {
    expire(key, (long) seconds);
  }

  void expire(String key, long seconds);

  void expireAt(String key, long unixTime);

  void ttl(String key);

  void pttl(String key);

  void touch(String... keys);

  void setbit(String key, long offset, boolean value);

  void setbit(String key, long offset, String value);

  void getbit(String key, long offset);

  void setrange(String key, long offset, String value);

  void getrange(String key, long startOffset, long endOffset);

  void move(String key, int dbIndex);

  void getSet(String key, String value);

  void mget(String... keys);

  void setnx(String key, String value);

  /**
   * @deprecated Use {@link #setex(java.lang.String, long, java.lang.String)}.
   */
  @Deprecated
  default void setex(String key, int seconds, String value) {
    setex(key, (long) seconds, value);
  }

  void setex(String key, long seconds, String value);

  void mset(String... keysvalues);

  void msetnx(String... keysvalues);

  void decrBy(String key, long decrement);

  void decr(String key);

  void incrBy(String key, long increment);

  void incrByFloat(String key, double increment);

  void incr(String key);

  void append(String key, String value);

  void substr(String key, int start, int end);

  void hset(String key, String field, String value);

  void hget(String key, String field);

  void hset(String key, Map<String, String> hash);

  void hsetnx(String key, String field, String value);

  void hmset(String key, Map<String, String> hash);

  void hmget(String key, String... fields);

  void hincrBy(String key, String field, long value);

  void hincrByFloat(String key, String field, double value);

  void hexists(String key, String field);

  void hdel(String key, String... fields);

  void hlen(String key);

  void hkeys(String key);

  void hvals(String key);

  void hrandfield(String key);

  void hrandfield(String key, long count);

  void hrandfieldWithValues(String key, long count);

  void hgetAll(String key);

  void rpush(String key, String... strings);

  void lpush(String key, String... strings);

  void llen(String key);

  void lrange(String key, long start, long stop);

  void ltrim(String key, long start, long stop);

  void lindex(String key, long index);

  void lset(String key, long index, String value);

  void lrem(String key, long count, String value);

  void lpop(String key);

  void lpop(String key, int count);

  void lpos(String key, String element);

  void lpos(String key, String element, LPosParams params);

  void lpos(String key, String element, LPosParams params, long count);

  void rpop(String key);

  void rpop(String key, int count);

  void rpoplpush(String srckey, String dstkey);

  void sadd(String key, String... members);

  void smembers(String key);

  void srem(String key, String... member);

  void spop(String key);

  void spop(String key, long count);

  void smove(String srckey, String dstkey, String member);

  void scard(String key);

  void sismember(String key, String member);

  void smismember(String key, String... members);

  void sinter(String... keys);

  void sinterstore(String dstkey, String... keys);

  void sunion(String... keys);

  void sunionstore(String dstkey, String... keys);

  void sdiff(String... keys);

  void sdiffstore(String dstkey, String... keys);

  void zdiff(String... keys);

  void zdiffWithScores(String... keys);

  void srandmember(String key);

  void zadd(String key, double score, String member);

  void zadd(String key, double score, String member, ZAddParams params);

  void zadd(String key, Map<String, Double> scoreMembers);

  void zadd(String key, Map<String, Double> scoreMembers, ZAddParams params);

  void zaddIncr(String key, double score, String member, ZAddParams params);

  void zdiffStore(String dstkey, String... keys);

  void zrange(String key, long start, long stop);

  void zrem(String key, String... members);

  void zincrby(String key, double increment, String member);

  void zincrby(String key, double increment, String member, ZIncrByParams params);

  void zrank(String key, String member);

  void zrevrank(String key, String member);

  void zrevrange(String key, long start, long stop);

  void zrangeWithScores(String key, long start, long stop);

  void zrevrangeWithScores(String key, long start, long stop);

  void zrandmember(String key);

  void zrandmember(String key, long count);

  void zrandmemberWithScores(String key, long count);

  void zcard(String key);

  void zscore(String key, String member);

  void zmscore(String key, String... members);

  void zpopmax(String key);

  void zpopmax(String key, int count);

  void zpopmin(String key);

  void zpopmin(String key, long count);

  void watch(String... keys);

  void sort(String key);

  void sort(String key, SortingParams sortingParameters);

  void sort(String key, SortingParams sortingParameters, String dstkey);

  void sort(String key, String dstkey);

  void lmove(String srcKey, String dstKey, ListDirection from, ListDirection to);

  void blmove(String srcKey, String dstKey, ListDirection from, ListDirection to, double timeout);

  void blpop(String[] args);

  void blpop(int timeout, String... keys);

  void blpop(double timeout, String... keys);

  void brpop(String[] args);

  void brpop(int timeout, String... keys);

  void brpop(double timeout, String... keys);

  void brpoplpush(String source, String destination, int timeout);

  void bzpopmax(double timeout, String... keys);

  void bzpopmin(double timeout, String... keys);

  void zcount(String key, double min, double max);

  void zcount(String key, String min, String max);

  void zrangeByScore(String key, double min, double max);

  void zrangeByScore(String key, String min, String max);

  void zrangeByScore(String key, double min, double max, int offset, int count);

  void zrangeByScore(String key, String min, String max, int offset, int count);

  void zrangeByScoreWithScores(String key, double min, double max);

  void zrangeByScoreWithScores(String key, double min, double max, int offset, int count);

  void zrangeByScoreWithScores(String key, String min, String max);

  void zrangeByScoreWithScores(String key, String min, String max, int offset, int count);

  void zrevrangeByScore(String key, double max, double min);

  void zrevrangeByScore(String key, String max, String min);

  void zrevrangeByScore(String key, double max, double min, int offset, int count);

  void zrevrangeByScore(String key, String max, String min, int offset, int count);

  void zrevrangeByScoreWithScores(String key, double max, double min);

  void zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count);

  void zrevrangeByScoreWithScores(String key, String max, String min);

  void zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count);

  void zremrangeByRank(String key, long start, long stop);

  void zremrangeByScore(String key, double min, double max);

  void zremrangeByScore(String key, String min, String max);

  void zunion(ZParams params, String... keys);

  void zunionWithScores(ZParams params, String... keys);

  void zunionstore(String dstkey, String... sets);

  void zunionstore(String dstkey, ZParams params, String... sets);

  void zinter(ZParams params, String... keys);

  void zinterWithScores(ZParams params, String... keys);

  void zinterstore(String dstkey, String... sets);

  void zinterstore(String dstkey, ZParams params, String... sets);

  void strlen(String key);

  void lpushx(String key, String... string);

  void persist(String key);

  void rpushx(String key, String... string);

  void echo(String string);

  void linsert(String key, ListPosition where, String pivot, String value);

  void bgrewriteaof();

  void bgsave();

  void lastsave();

  void save();

  void configSet(String parameter, String value);

  void configGet(String pattern);

  void configResetStat();

  void multi();

  void exec();

  void discard();

  void objectRefcount(String key);

  void objectIdletime(String key);

  void objectEncoding(String key);

  void objectHelp();

  void objectFreq(String key);

  void bitcount(String key);

  void bitcount(String key, long start, long end);

  void bitop(BitOP op, String destKey, String... srcKeys);

  void dump(String key);

  /**
   * @deprecated Use {@link #restore(java.lang.String, long, byte[])}.
   */
  @Deprecated
  default void restore(String key, int ttl, byte[] serializedValue) {
    restore(key, (long) ttl, serializedValue);
  }

  void restore(String key, long ttl, byte[] serializedValue);

  /**
   * @deprecated Use {@link #restore(java.lang.String, long, byte[], redis.clients.jedis.params.RestoreParams)}.
   */
  @Deprecated
  default void restoreReplace(String key, int ttl, byte[] serializedValue) {
    restoreReplace(key, (long) ttl, serializedValue);
  }

  /**
   * @deprecated Use {@link #restore(java.lang.String, long, byte[], redis.clients.jedis.params.RestoreParams)}.
   */
  @Deprecated
  void restoreReplace(String key, long ttl, byte[] serializedValue);

  void restore(String key, long ttl, byte[] serializedValue, RestoreParams params);

  void scan(String cursor, ScanParams params);

  void hscan(String key, String cursor, ScanParams params);

  void sscan(String key, String cursor, ScanParams params);

  void zscan(String key, String cursor, ScanParams params);

  void waitReplicas(int replicas, long timeout);

  /**
   * Used for BITFIELD Redis command
   * @param key
   * @param arguments
   */
  void bitfield(String key, String... arguments);

  void bitfieldReadonly(String key, String... arguments);

  /**
   * Used for HSTRLEN Redis command
   * @param key
   * @param field
   */
  void hstrlen(String key, String field);

  void migrate(String host, int port, String key, int destinationDB, int timeout);

  void migrate(String host, int port, int destinationDB, int timeout, MigrateParams params, String... keys);

  void clientKill(String ipPort);

  void clientKill(String ip, int port);

  void clientKill(ClientKillParams params);

  void clientGetname();

  void clientList();

  void clientList(long... clientIds);

  void clientInfo();

  void clientSetname(String name);

  void clientId();

  void clientUnblock(long clientId, UnblockType unblockType);

  void memoryDoctor();

  void xadd(String key, StreamEntryID id, Map<String, String> hash, long maxLen, boolean approximateLength);

  void xadd(String key, Map<String, String> hash, XAddParams params);

  void xlen(String key);

  void xrange(String key, StreamEntryID start, StreamEntryID end);

  void xrange(String key, StreamEntryID start, StreamEntryID end, int count);

  /**
   * @deprecated Use {@link #xrange(java.lang.String, redis.clients.jedis.StreamEntryID, redis.clients.jedis.StreamEntryID, int)}.
   */
  @Deprecated
  void xrange(String key, StreamEntryID start, StreamEntryID end, long count);

  void xrevrange(String key, StreamEntryID end, StreamEntryID start);

  void xrevrange(String key, StreamEntryID end, StreamEntryID start, int count);

  /**
   * @deprecated This method will be removed due to bug regarding {@code block} param. Use
   * {@link #xread(redis.clients.jedis.params.XReadParams, java.util.Map)}.
   */
  @Deprecated
  void xread(int count, long block, Entry<String, StreamEntryID>... streams);

  void xread(XReadParams params, Map<String, StreamEntryID> streams);

  void xack(String key, String group, StreamEntryID... ids);

  void xgroupCreate(String key, String consumer, StreamEntryID id, boolean makeStream);

  void xgroupSetID(String key, String consumer, StreamEntryID id);

  void xgroupDestroy(String key, String consumer);

  void xgroupDelConsumer(String key, String consumer, String consumerName);

  void xdel(String key, StreamEntryID... ids);

  void xtrim(String key, long maxLen, boolean approximateLength);

  void xtrim(String key, XTrimParams params);

  /**
   * @deprecated This method will be removed due to bug regarding {@code block} param. Use
   * {@link #xreadGroup(java.lang.String, java.lang.String, redis.clients.jedis.params.XReadGroupParams, java.util.Map)}.
   */
  @Deprecated
  void xreadGroup(String groupname, String consumer, int count, long block, boolean noAck, Entry<String, StreamEntryID>... streams);

  void xreadGroup(String groupname, String consumer, XReadGroupParams params, Map<String, StreamEntryID> streams);

  void xpending(String key, String groupname);

  void xpending(String key, String groupname, StreamEntryID start, StreamEntryID end, int count, String consumername);

  void xpending(String key, String groupname, XPendingParams params);

  void xclaim(String key, String group, String consumername, long minIdleTime, long newIdleTime,
      int retries, boolean force, StreamEntryID... ids);

  void xclaim(String key, String group, String consumername, long minIdleTime, XClaimParams params,
      StreamEntryID... ids);

  void xclaimJustId(String key, String group, String consumername, long minIdleTime,
      XClaimParams params, StreamEntryID... ids);

  void xautoclaim(String key, String group, String consumerName,
      long minIdleTime, StreamEntryID start, XAutoClaimParams params);

  void xautoclaimJustId(String key, String group, String consumerName,
      long minIdleTime, StreamEntryID start, XAutoClaimParams params);

  void xinfoStream (String key);

  void xinfoGroup (String key);

  void xinfoConsumers (String key, String group);
}
