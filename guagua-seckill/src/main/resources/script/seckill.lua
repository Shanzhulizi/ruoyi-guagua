-- KEYS[1]: 商品库存key
-- KEYS[2]: 用户已购key
-- ARGV[1]: 用户ID

local stock = redis.call("get", KEYS[1])
if not stock then
    return 0
end
stock = tonumber(stock)
if stock <= 0 then
    return 0
end

if redis.call("sismember", KEYS[2], ARGV[1]) == 1 then
    return 2
end

redis.call("decr", KEYS[1])
redis.call("sadd", KEYS[2], ARGV[1])

-- 添加用户日志（List结构）
local logKey = "seckill:log:" .. KEYS[1]
redis.call("rpush", logKey, ARGV[1])

return 1
