package com.example.dijingyu.app_demo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：邸某某
 * 时间：2019/5/6
 */

public class BanMiBean implements Serializable{

    /**
     * code : 0
     * desc :
     * result : {"page":1,"limit":20,"count":12,"banmi":[{"id":57,"name":"吴晓波","location":"杭州","occupation":"著名财经作家 青年领袖","introduction":"大家好，我是吴晓波。作为一个写字的人，深知\"读万卷书\"的重要，不过读书读到我这个年龄，有时候会生出无书可读的感叹，这时我常会选择\"行万里路\"，在旅程中寻找书本中无法获得的感受。\n\n无论目的和方式如何多变，旅行最终将落脚在体验二字，这是我和伴米旅行的共识，也是我们力求达到的效果。就算和千万人去了同一个地方，你也能通过我们的分享获得独有的体验，而这些体验会积累成为收获和知识，让旅行从一种短暂的休闲方式变成一种持久且让人成长的爱好。","following":5648,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1520393823697_c68268595efc905fc397781b6cde03c7.jpg","isFollowed":true},{"id":28,"name":"李炜","location":"北京","occupation":"蜻蜓FM名嘴，知名文化记者","introduction":"你好，我是李炜，知名文化记者。闲来无事最喜欢与两三好友对坐闲侃，聊聊历史八卦、文化趣事。我向来讲求有枣没枣儿，先打三杆子，搂草打兔子，咱们讲哪算哪儿。我不是专家，不谈之乎者也洋洋洒洒。或沉土中千载，或睹世间沧桑穿朝越代，它们背后的故事远比宫斗精彩。浩渺的历史文化中，总有有趣的故事让人折服与感悟，拿出一点时间，我们一起去看世界之大。觉得有趣，图您一乐呵，万一不妥，请您多包涵。","following":4627,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1513046399561_9d3b02cad0eddee4347c93f43b3e5a7a.jpg","isFollowed":true},{"id":25,"name":"王自如","location":"深圳","occupation":"中国电子产品测评第一人","introduction":"Hello你好，我是王自如。借着在日本转机的机会，我来到了东京\u2014\u2014这个全球非常重要的科技之都。我个人呢也没来过日本，所以此次顺道前来朝拜一下。两天的时间，我几乎逛遍了东京所有与高新科技相关的景点，的确大开眼界，这次也希望通过伴米旅行，将这段行程分享给大家。","following":1452,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511748843296_eb8d5337fa7223727eb35fae8b29416e.jpg","isFollowed":true},{"id":9,"name":"Isa","location":"长野县轻井泽","occupation":"互联网公司CEO 世界旅行家","introduction":"大家好，我叫Isa，是一家互联网创业公司创始人兼CEO。千万不要一听我头衔，就以为我是个公务缠身的工作狂。工作之余，去世界各地旅行也是我的挚爱，至今，我已经去过56个国家。我是个彻头彻尾的行动派，风景没有捷径，即刻出发也不该有任何借口。跟上我的脚步，我会带你重新认识这个世界。","following":3458,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511750046703_ae0388aa6532e8f3d9765c1ca4a5971d.png","isFollowed":true},{"id":10,"name":"林竹","location":"东京","occupation":"私绘本漫画家","introduction":"大家好，我是林竹，喜欢自称林小姐，林小姐有很多面：是绘本漫画家，是桑巴舞者，是自诩的美食家（四川人基因，对味道的敏锐度之高，自认童叟无欺）。但我最喜欢的身份，是pro的旅行作家，用我身体的每一部分去感受世界不同的地方。对林小姐来说，奢侈地玩不是烧钱，也不是胡吃海喝，而是选个心尖上的城市，预备出足够的时间，保证\u201c玩\u201d的每一刻都是悠长而缓慢的。","following":5321,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511749340164_47c36b8763f55cfd7c4fac3433cd5eaf.jpg","isFollowed":true},{"id":12,"name":"张德芬","location":"台湾","occupation":"华语世界首席身心灵作家","introduction":"大家好，我是张德芬。我是个专注于心灵旅行和瑜伽研修的身心灵作家。也许你读过我的书，也许我在媒体上的某段话曾给过你心灵的触动，但这一次，我想试试这种新的方式\u2014\u2014在伴米旅行、在路上，和你用灵魂交流。快跟着我的线路出发吧，踏入广阔的世界，让内心无比舒展，尽情追寻生命的意义。","following":3476,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511749429031_7a82794c775b514f0502fd6a5a4edb40.jpg","isFollowed":true},{"id":13,"name":"东山鹿溪","location":"大阪","occupation":"大阪大学历史学博士","introduction":"大家好，我叫东山鹿溪，大阪大学历史学博士，主要研究日本中世佛教史，同时在日本兼职一名新闻编辑。在日本生活的五年时间里，我一边研究佛学历史，一边研读了大量佛经，自此一头钻进了日本佛教的世界中。好吧，可能你会觉得这些有些枯燥？但相信你也一定被日本佛堂寺院中的美景惊艳过吧！那不如跟我一起，带着一颗看风景的心，去欣赏独属于日本的禅意吧。","following":2327,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511749531107_762df69ad710b36c0ce4d3e588a3f0c1.jpg","isFollowed":true},{"id":54,"name":"JoJo","location":"苏州","occupation":"全球深度游旅行玩家","introduction":"你好，我是旅行玩家JoJo。从高中开始就在澳洲西部的珀斯留学，大学转到了东部的墨尔本。在将近7年的澳洲学习生活中，每个周末只要有空，我就会和朋友去周边旅行。其中我最喜欢的就是在西澳自驾游，不仅景美，西澳人的朴实热情更是让我感觉亲切友好，内心温暖。所以假如你想要一个放松心情、放慢脚步的旅行，我一定会首先推荐你来西澳。\n\n直到现在，我对旅行的上瘾热度也一直未减，一年有将近一半的时间我都在世界各地深度旅行，目前已经流浪过十几个国家，除了澳洲，待得最久的地方就是韩国了。我跑遍了梨泰院、江南、圣水洞等等我喜欢的街区，掘地三尺，挖出了不少我喜欢的地方。尤其因为我喜欢住在当地人家里，和他们一起生活、交谈，这样可以让我最快速地体验到最地道的本地文化，发现最受当地人欢迎的地方。所以跟着我深度游，可以了解每座城市不一样的魅力哦。","following":1624,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1528685149853_08b015235b67fad16076b88f5b0b09c6.jpg","isFollowed":true},{"id":66,"name":"Sudy","location":"西班牙","occupation":"航海环球旅行规划师，摄影师","introduction":"你好，我是Sudy，一个热爱美食和旅行的摄影师，至今已走过了几十个国家和地区，上百座城市和村镇。\u201c环球旅行\u201d一直是我的梦想，所以两年前，我辞去了工作，用全部的积蓄买了一艘帆船，开始了我的航海环游世界之旅。现在也正和我的船长一起驾着帆船游在世界各地呢。我喜欢用镜头记录不同地方的风景和人文，给你分享我的旅程以及世界各地不同人群的生活方式，也希望在旅行中能够为我们居住的地球尽一份自己微薄的力量。\n\n我陆续会在\u201c伴米旅行\u201d上推出环游世界的各国独家私藏路线，如果你也对这世界好奇，想探索，就跟着我一起上船，航行在这个有趣的星球吧！","following":1192,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1524048812294_a9cd79447c501647fffac2420f0ba9af.jpg","isFollowed":true},{"id":29,"name":"Lily","location":"新疆","occupation":"专业旅行领队，旅行达人","introduction":"你好，我是Lily。一个在新疆出生、长大的部队军人后代，多年部队大院生活成就了我热情开朗的性格，喜欢到处走走看看，对世界一直充满好奇。平时经常自驾旅行，从新疆到内蒙，祖国的各种高山、草原、盆地都留下了我的足迹。从小在大院吃百家饭长大的我，对美食更是由衷热爱，喜欢去大街小巷探索各种美食，无论是传统清真，还是其他少数民族特色餐饮，或者是汉族饮食在各地的演变与流传，都十分有兴趣，并对这些美食背后的文化渊源充满好奇。最近几年，我成为一名专业的旅行领队，这让我对旅行中的美食、摄影、建筑等知识都有了更为专业和深刻的认知。\n此前，我在伴米推荐了我的故乡\u2014\u2014新疆线路，这次再带大家到内蒙古的赤峰附近转转。","following":905,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1519617135941_da364ac5872db56aa8f99777ce4655dd.jpg","isFollowed":true},{"id":7,"name":"库索","location":"大阪","occupation":"旅日作家","introduction":"这两年来，我一直流窜暴走于岛国各地。我爱镰仓的夏天，我爱冲绳的海，我爱北陆的日落，我爱东京的冷漠与温情，我爱濑户内的初见如归，更爱旅途中遇到的人们。\n\n晃荡在岛国的日子里，我终于意识到：旅行，是一所大学。学会旅行，就等于学会了自由，学会了去了解广阔的世界。那里没有游客，只有彼此相遇。","following":3288,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511750000857_f8d7035ccf9ef132b1eba9536a0eaf8d.jpg","isFollowed":true},{"id":63,"name":"高弘","location":"北京","occupation":"九十行深度旅行社区创始人","introduction":"高弘，热爱旅行，曾搭顺风车走川藏线，独自完成北京到威尼斯的丝绸之路自行车长途旅行，历时半年，行程一万五千公里。毕业于清华大学自动化系毕业，南加州大学工学硕士（USC），曾工作于EDS，Deloitte Consulting, KSA，i2等公司，从事企业IT咨询，曾作为ITUC系统服务公司创始人之一。\n现为九十度深度旅行社区创始人，开启\u201c主题体验式旅行\u201d新时代。九十行是由一群热爱在路上的行者组成的深度挖掘线路，探讨本地文化，生态，人文等特质的旅行社区。","following":1023,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1523179198822_4cbbbf69b9b03b154a290ca8050b916e.jpeg","isFollowed":true}]}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        /**
         * page : 1
         * limit : 20
         * count : 12
         * banmi : [{"id":57,"name":"吴晓波","location":"杭州","occupation":"著名财经作家 青年领袖","introduction":"大家好，我是吴晓波。作为一个写字的人，深知\"读万卷书\"的重要，不过读书读到我这个年龄，有时候会生出无书可读的感叹，这时我常会选择\"行万里路\"，在旅程中寻找书本中无法获得的感受。\n\n无论目的和方式如何多变，旅行最终将落脚在体验二字，这是我和伴米旅行的共识，也是我们力求达到的效果。就算和千万人去了同一个地方，你也能通过我们的分享获得独有的体验，而这些体验会积累成为收获和知识，让旅行从一种短暂的休闲方式变成一种持久且让人成长的爱好。","following":5648,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1520393823697_c68268595efc905fc397781b6cde03c7.jpg","isFollowed":true},{"id":28,"name":"李炜","location":"北京","occupation":"蜻蜓FM名嘴，知名文化记者","introduction":"你好，我是李炜，知名文化记者。闲来无事最喜欢与两三好友对坐闲侃，聊聊历史八卦、文化趣事。我向来讲求有枣没枣儿，先打三杆子，搂草打兔子，咱们讲哪算哪儿。我不是专家，不谈之乎者也洋洋洒洒。或沉土中千载，或睹世间沧桑穿朝越代，它们背后的故事远比宫斗精彩。浩渺的历史文化中，总有有趣的故事让人折服与感悟，拿出一点时间，我们一起去看世界之大。觉得有趣，图您一乐呵，万一不妥，请您多包涵。","following":4627,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1513046399561_9d3b02cad0eddee4347c93f43b3e5a7a.jpg","isFollowed":true},{"id":25,"name":"王自如","location":"深圳","occupation":"中国电子产品测评第一人","introduction":"Hello你好，我是王自如。借着在日本转机的机会，我来到了东京\u2014\u2014这个全球非常重要的科技之都。我个人呢也没来过日本，所以此次顺道前来朝拜一下。两天的时间，我几乎逛遍了东京所有与高新科技相关的景点，的确大开眼界，这次也希望通过伴米旅行，将这段行程分享给大家。","following":1452,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511748843296_eb8d5337fa7223727eb35fae8b29416e.jpg","isFollowed":true},{"id":9,"name":"Isa","location":"长野县轻井泽","occupation":"互联网公司CEO 世界旅行家","introduction":"大家好，我叫Isa，是一家互联网创业公司创始人兼CEO。千万不要一听我头衔，就以为我是个公务缠身的工作狂。工作之余，去世界各地旅行也是我的挚爱，至今，我已经去过56个国家。我是个彻头彻尾的行动派，风景没有捷径，即刻出发也不该有任何借口。跟上我的脚步，我会带你重新认识这个世界。","following":3458,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511750046703_ae0388aa6532e8f3d9765c1ca4a5971d.png","isFollowed":true},{"id":10,"name":"林竹","location":"东京","occupation":"私绘本漫画家","introduction":"大家好，我是林竹，喜欢自称林小姐，林小姐有很多面：是绘本漫画家，是桑巴舞者，是自诩的美食家（四川人基因，对味道的敏锐度之高，自认童叟无欺）。但我最喜欢的身份，是pro的旅行作家，用我身体的每一部分去感受世界不同的地方。对林小姐来说，奢侈地玩不是烧钱，也不是胡吃海喝，而是选个心尖上的城市，预备出足够的时间，保证\u201c玩\u201d的每一刻都是悠长而缓慢的。","following":5321,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511749340164_47c36b8763f55cfd7c4fac3433cd5eaf.jpg","isFollowed":true},{"id":12,"name":"张德芬","location":"台湾","occupation":"华语世界首席身心灵作家","introduction":"大家好，我是张德芬。我是个专注于心灵旅行和瑜伽研修的身心灵作家。也许你读过我的书，也许我在媒体上的某段话曾给过你心灵的触动，但这一次，我想试试这种新的方式\u2014\u2014在伴米旅行、在路上，和你用灵魂交流。快跟着我的线路出发吧，踏入广阔的世界，让内心无比舒展，尽情追寻生命的意义。","following":3476,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511749429031_7a82794c775b514f0502fd6a5a4edb40.jpg","isFollowed":true},{"id":13,"name":"东山鹿溪","location":"大阪","occupation":"大阪大学历史学博士","introduction":"大家好，我叫东山鹿溪，大阪大学历史学博士，主要研究日本中世佛教史，同时在日本兼职一名新闻编辑。在日本生活的五年时间里，我一边研究佛学历史，一边研读了大量佛经，自此一头钻进了日本佛教的世界中。好吧，可能你会觉得这些有些枯燥？但相信你也一定被日本佛堂寺院中的美景惊艳过吧！那不如跟我一起，带着一颗看风景的心，去欣赏独属于日本的禅意吧。","following":2327,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511749531107_762df69ad710b36c0ce4d3e588a3f0c1.jpg","isFollowed":true},{"id":54,"name":"JoJo","location":"苏州","occupation":"全球深度游旅行玩家","introduction":"你好，我是旅行玩家JoJo。从高中开始就在澳洲西部的珀斯留学，大学转到了东部的墨尔本。在将近7年的澳洲学习生活中，每个周末只要有空，我就会和朋友去周边旅行。其中我最喜欢的就是在西澳自驾游，不仅景美，西澳人的朴实热情更是让我感觉亲切友好，内心温暖。所以假如你想要一个放松心情、放慢脚步的旅行，我一定会首先推荐你来西澳。\n\n直到现在，我对旅行的上瘾热度也一直未减，一年有将近一半的时间我都在世界各地深度旅行，目前已经流浪过十几个国家，除了澳洲，待得最久的地方就是韩国了。我跑遍了梨泰院、江南、圣水洞等等我喜欢的街区，掘地三尺，挖出了不少我喜欢的地方。尤其因为我喜欢住在当地人家里，和他们一起生活、交谈，这样可以让我最快速地体验到最地道的本地文化，发现最受当地人欢迎的地方。所以跟着我深度游，可以了解每座城市不一样的魅力哦。","following":1624,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1528685149853_08b015235b67fad16076b88f5b0b09c6.jpg","isFollowed":true},{"id":66,"name":"Sudy","location":"西班牙","occupation":"航海环球旅行规划师，摄影师","introduction":"你好，我是Sudy，一个热爱美食和旅行的摄影师，至今已走过了几十个国家和地区，上百座城市和村镇。\u201c环球旅行\u201d一直是我的梦想，所以两年前，我辞去了工作，用全部的积蓄买了一艘帆船，开始了我的航海环游世界之旅。现在也正和我的船长一起驾着帆船游在世界各地呢。我喜欢用镜头记录不同地方的风景和人文，给你分享我的旅程以及世界各地不同人群的生活方式，也希望在旅行中能够为我们居住的地球尽一份自己微薄的力量。\n\n我陆续会在\u201c伴米旅行\u201d上推出环游世界的各国独家私藏路线，如果你也对这世界好奇，想探索，就跟着我一起上船，航行在这个有趣的星球吧！","following":1192,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1524048812294_a9cd79447c501647fffac2420f0ba9af.jpg","isFollowed":true},{"id":29,"name":"Lily","location":"新疆","occupation":"专业旅行领队，旅行达人","introduction":"你好，我是Lily。一个在新疆出生、长大的部队军人后代，多年部队大院生活成就了我热情开朗的性格，喜欢到处走走看看，对世界一直充满好奇。平时经常自驾旅行，从新疆到内蒙，祖国的各种高山、草原、盆地都留下了我的足迹。从小在大院吃百家饭长大的我，对美食更是由衷热爱，喜欢去大街小巷探索各种美食，无论是传统清真，还是其他少数民族特色餐饮，或者是汉族饮食在各地的演变与流传，都十分有兴趣，并对这些美食背后的文化渊源充满好奇。最近几年，我成为一名专业的旅行领队，这让我对旅行中的美食、摄影、建筑等知识都有了更为专业和深刻的认知。\n此前，我在伴米推荐了我的故乡\u2014\u2014新疆线路，这次再带大家到内蒙古的赤峰附近转转。","following":905,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1519617135941_da364ac5872db56aa8f99777ce4655dd.jpg","isFollowed":true},{"id":7,"name":"库索","location":"大阪","occupation":"旅日作家","introduction":"这两年来，我一直流窜暴走于岛国各地。我爱镰仓的夏天，我爱冲绳的海，我爱北陆的日落，我爱东京的冷漠与温情，我爱濑户内的初见如归，更爱旅途中遇到的人们。\n\n晃荡在岛国的日子里，我终于意识到：旅行，是一所大学。学会旅行，就等于学会了自由，学会了去了解广阔的世界。那里没有游客，只有彼此相遇。","following":3288,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511750000857_f8d7035ccf9ef132b1eba9536a0eaf8d.jpg","isFollowed":true},{"id":63,"name":"高弘","location":"北京","occupation":"九十行深度旅行社区创始人","introduction":"高弘，热爱旅行，曾搭顺风车走川藏线，独自完成北京到威尼斯的丝绸之路自行车长途旅行，历时半年，行程一万五千公里。毕业于清华大学自动化系毕业，南加州大学工学硕士（USC），曾工作于EDS，Deloitte Consulting, KSA，i2等公司，从事企业IT咨询，曾作为ITUC系统服务公司创始人之一。\n现为九十度深度旅行社区创始人，开启\u201c主题体验式旅行\u201d新时代。九十行是由一群热爱在路上的行者组成的深度挖掘线路，探讨本地文化，生态，人文等特质的旅行社区。","following":1023,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1523179198822_4cbbbf69b9b03b154a290ca8050b916e.jpeg","isFollowed":true}]
         */

        private int page;
        private int limit;
        private int count;
        private List<BanmiBean> banmi;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<BanmiBean> getBanmi() {
            return banmi;
        }

        public void setBanmi(List<BanmiBean> banmi) {
            this.banmi = banmi;
        }

        public static class BanmiBean implements Serializable{
            /**
             * id : 57
             * name : 吴晓波
             * location : 杭州
             * occupation : 著名财经作家 青年领袖
             * introduction : 大家好，我是吴晓波。作为一个写字的人，深知"读万卷书"的重要，不过读书读到我这个年龄，有时候会生出无书可读的感叹，这时我常会选择"行万里路"，在旅程中寻找书本中无法获得的感受。

             无论目的和方式如何多变，旅行最终将落脚在体验二字，这是我和伴米旅行的共识，也是我们力求达到的效果。就算和千万人去了同一个地方，你也能通过我们的分享获得独有的体验，而这些体验会积累成为收获和知识，让旅行从一种短暂的休闲方式变成一种持久且让人成长的爱好。
             * following : 5648
             * photo : http://cdn.banmi.com/banmiapp/rahdna/1520393823697_c68268595efc905fc397781b6cde03c7.jpg
             * isFollowed : true
             */

            private int id;
            private String name;
            private String location;
            private String occupation;
            private String introduction;
            private int following;
            private String photo;
            private boolean isFollowed;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public int getFollowing() {
                return following;
            }

            public void setFollowing(int following) {
                this.following = following;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public boolean isIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(boolean isFollowed) {
                this.isFollowed = isFollowed;
            }
        }
    }
}
