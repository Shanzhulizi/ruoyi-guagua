-- KEYS[1] 商品库存 key，例如 seckill:stock:1
-- KEYS[2] 用户是否抢购过 key，例如 seckill:user:1:123
-- ARGV[1] 用户ID

-- 判断库存是否充足
local stock = tonumber(redis.call("get", KEYS[1]))
if stock <= 0 then
    return 0
end

-- 判断用户是否已经抢购过
if redis.call("sismember", KEYS[2], ARGV[1]) == 1 then
    return 2
end

-- 扣减库存
redis.call("decr", KEYS[1])

-- 记录用户已抢购
redis.call("sadd", KEYS[2], ARGV[1])

-- 返回成功
return 1
