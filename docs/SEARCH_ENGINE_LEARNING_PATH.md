# 搜索引擎技术学习路径

目标：从零基础到能独立设计、实现、评估和优化生产级搜索系统。学习方式以工程项目驱动，每个阶段都产出可运行系统、指标报告或设计文档。

## 0. 总体路线

搜索引擎不是单一算法，而是一条完整链路：

1. 数据获取：爬虫、日志、数据库同步、增量更新。
2. 文本处理：分词、归一化、停用词、同义词、拼写纠错、实体识别。
3. 索引结构：倒排索引、词典、posting list、跳表、位置索引、压缩、段合并。
4. 召回：Boolean、BM25、短语查询、过滤、向量召回、混合召回。
5. 排序：字段权重、业务特征、Learning to Rank、重排、个性化。
6. 评估：Precision、Recall、MAP、MRR、NDCG、在线 A/B、点击日志偏差。
7. 系统设计：分片、副本、缓存、近实时索引、容灾、监控、成本和延迟。
8. 现代搜索：语义搜索、HNSW、RAG 检索、稀疏向量、混合搜索、reranker。

## 1. 推荐主资料

### 算法和理论

- Stanford CS276: Information Retrieval and Web Search  
  https://web.stanford.edu/class/cs276/index.html
- Introduction to Information Retrieval, Manning / Raghavan / Schuetze  
  https://nlp.stanford.edu/IR-book/
- TREC / NIST 信息检索评估体系  
  https://trec.nist.gov/about.html

### 工程系统

- Apache Lucene：理解倒排索引、Query、Analyzer、BM25 的底层实现  
  https://lucene.apache.org/
- Elasticsearch 官方文档：生产级分布式搜索和向量搜索  
  https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html
- Apache Solr 官方文档：Lucene 生态、LTR、向量检索  
  https://solr.apache.org/guide/solr/latest/
- OpenSearch 官方文档：混合搜索和开源搜索引擎工程  
  https://docs.opensearch.org/latest/
- Vespa 官方文档：搜索、推荐、向量检索、在线特征和排序系统  
  https://docs.vespa.ai/
- Faiss：高性能向量相似度搜索  
  https://github.com/facebookresearch/faiss/wiki
- Anserini / Pyserini：可复现实验型 IR 工具链  
  https://github.com/castorini/anserini

## 2. 阶段路线

### 阶段 A：编程和基础补齐，2-4 周

你需要先能写小型数据处理程序，否则搜索引擎很多细节会看不懂。

掌握：

- Python 基础：文件处理、类、迭代器、字典、排序、堆。
- 基础数据结构：数组、链表、哈希表、堆、跳表、Trie。
- 基础概率统计：均值、分布、条件概率、采样、评估指标。
- HTTP 和 JSON：为后续爬虫、API、搜索服务做准备。

实践项目：

- 写一个本地文档搜索脚本：读取一批 Markdown / TXT 文件，按关键词返回匹配行。
- 加入简单打分：标题命中 +5，正文命中 +1，按分数排序。
- 输出每次查询的耗时。

验收标准：

- 能解释为什么扫描全量文档在数据量变大后会慢。
- 能用程序记录查询耗时，并用数据证明瓶颈。

### 阶段 B：从零实现迷你搜索引擎，4-6 周

这是最重要的入门阶段。不要一上来只用 Elasticsearch，否则会绕过核心原理。

掌握：

- 倒排索引：term -> doc list。
- 分词、大小写归一化、停用词。
- TF、IDF、TF-IDF、BM25。
- 短语查询：位置索引。
- Top-K：堆、WAND / Block-Max WAND 的基本思想。
- 索引压缩：delta encoding、variable byte、bit packing 的直觉。

实践项目：

- 实现 `mini-search`：
  - 输入：1000-10000 篇文档。
  - 索引：生成词典、倒排表、词频、文档长度。
  - 查询：支持 AND、OR、短语查询、字段权重。
  - 排序：先 TF-IDF，再 BM25。
  - 调试：给每个结果输出 explain，说明分数怎么来的。

验收标准：

- 能手写 BM25 的主要公式，并解释 `k1` 和 `b` 的作用。
- 能解释为什么倒排索引比全表扫描快。
- 能解释短语查询为什么需要位置索引。
- 能写一份结果分析：哪些 query 排得不好，原因是什么。

### 阶段 C：用 Lucene / Elasticsearch 做真实搜索服务，6-8 周

这一阶段开始靠近主流工程实践。

掌握：

- Analyzer：tokenizer、token filter、char filter。
- Mapping / schema：text、keyword、numeric、date、dense_vector。
- BM25、字段 boost、filter context、query context。
- 近实时索引、segment、refresh、merge。
- 分片、副本、路由、缓存、分页、聚合。
- explain、profile、slow log、索引大小和查询延迟。

实践项目：

- 做一个电商商品搜索：
  - 数据：商品标题、类目、品牌、描述、价格、销量、库存、上架时间。
  - 搜索：关键词搜索、类目筛选、价格排序、拼写纠错、同义词。
  - 排序：BM25 + 字段权重 + 销量/新鲜度重排。
  - API：提供 `/search?q=...`。
  - 观测：记录 P50/P95 延迟、命中数量、Top10 结果。

验收标准：

- 能解释 query 和 filter 的区别。
- 能根据 explain 输出定位为什么某个文档排第一。
- 能设计索引 mapping，并说明为什么某字段用 `keyword` 而不是 `text`。
- 能把一次慢查询拆解为分析、召回、排序、网络、序列化几个环节。

### 阶段 D：搜索质量评估和相关性优化，6-8 周

搜索专家和普通工程师的分水岭在这里：不能只看“能搜到”，要能证明“搜得好”。

掌握：

- 离线评估：Precision@K、Recall@K、MRR、MAP、NDCG。
- 标注集构建：query-document relevance labels。
- 查询分类：导航型、信息型、交易型、长尾 query、无结果 query。
- 错误分析：bad case taxonomy。
- 点击日志：CTR、位置偏差、展示偏差、反事实评估基础。

实践项目：

- 给电商搜索建立一个小型评估集：
  - 100 个 query。
  - 每个 query 标注 20-50 个候选商品相关性，0/1/2/3 分。
  - 对比三版排序：BM25、BM25+业务权重、BM25+重排。
  - 输出 NDCG@10、MRR、失败案例。

验收标准：

- 能解释为什么只看点击率会误导排序优化。
- 能讲清楚 NDCG 适合什么场景。
- 能用 bad case 驱动下一轮索引、召回或排序改进。

### 阶段 E：Learning to Rank 和重排，8-10 周

这一阶段开始进入高级相关性工程。

掌握：

- 特征工程：文本匹配、字段匹配、流行度、质量分、新鲜度、个性化。
- Pointwise / Pairwise / Listwise 排序。
- LambdaMART、RankNet、RankSVM 的基本思想。
- XGBoost / LightGBM 排序模型。
- 两阶段排序：一阶段高召回，二阶段高质量重排。
- 特征泄漏、训练/线上一致性、位置偏差。

实践项目：

- 基于阶段 C 的商品搜索做 LTR：
  - 第一阶段：BM25 召回 Top 200。
  - 特征：BM25 分、标题命中数、品牌匹配、类目匹配、销量、价格、上架时间。
  - 模型：XGBoost `rank:ndcg` 或 LightGBM lambdarank。
  - 第二阶段：重排 Top 200，返回 Top 20。
  - 对比：NDCG@10、P95 延迟、bad case。

验收标准：

- 能解释为什么 LTR 通常不是替代召回，而是重排。
- 能说明哪些特征适合线上实时计算，哪些应该离线预计算。
- 能识别模型让相关性变差的常见原因。

### 阶段 F：语义搜索、向量检索、混合搜索，8-12 周

现代搜索不能只停留在 BM25。

掌握：

- Embedding：文本向量、余弦相似度、点积、归一化。
- ANN：HNSW、IVF、PQ、量化、召回率和延迟权衡。
- Faiss / Elasticsearch dense_vector / OpenSearch neural search / Vespa nearestNeighbor。
- Hybrid search：BM25 + vector + RRF / score normalization。
- Sparse neural retrieval：SPLADE / ELSER 类思想。
- Reranker：cross-encoder、LLM reranking、成本控制。

实践项目：

- 给商品搜索加入语义搜索：
  - 用 embedding 模型生成商品向量。
  - 建立向量索引。
  - 对比三种方案：BM25、向量搜索、BM25+向量混合。
  - 加入 RRF 或归一化融合。
  - 对长尾 query 和同义表达做专项评估。

验收标准：

- 能解释 HNSW 中 `M`、`efConstruction`、`efSearch` 对索引大小、构建时间、召回和延迟的影响。
- 能解释为什么向量搜索不一定比 BM25 好。
- 能为不同业务 query 选择 BM25、向量、混合或 reranker。

### 阶段 G：分布式搜索系统设计，8-12 周

这一阶段目标是能做架构设计，而不是只调 API。

掌握：

- 分片策略：按 doc id、租户、时间、业务域路由。
- 副本和高可用。
- 写入链路：bulk、refresh、segment merge、translog。
- 查询链路：coordinator、fan-out、top-k merge、cache。
- 热点 query、深分页、排序字段、聚合成本。
- 多集群、跨地域、灾备、回滚。
- 安全：权限过滤、字段级权限、多租户隔离。

实践项目：

- 设计并压测一个生产搜索架构：
  - 1000 万商品或文档规模的估算方案。
  - QPS、P95、索引吞吐、存储成本估算。
  - 设计分片、副本、冷热数据、缓存、降级。
  - 用 Rally、JMeter、k6 或自写压测脚本验证关键路径。

验收标准：

- 能说明为什么分片不是越多越好。
- 能设计深分页替代方案：search_after、scroll、point in time。
- 能从 CPU、heap、page cache、IO、network 分析瓶颈。

### 阶段 H：专家阶段，持续 6-18 个月

这一阶段不再是“学知识点”，而是持续做高质量问题求解。

方向：

- 读 Lucene 源码：Analyzer、IndexWriter、Segment、Query、Collector、Similarity。
- 读 Elasticsearch / OpenSearch 的分布式查询和写入路径。
- 跟踪 SIGIR、WWW、WSDM、KDD、TREC、arXiv IR / NLP 检索论文。
- 做线上搜索质量体系：标注平台、评估平台、A/B、实验平台。
- 深入一个业务域：电商、企业知识库、代码搜索、日志搜索、法律/医学/金融搜索。
- 做成本优化：索引压缩、冷热分层、缓存、向量量化、rerank 裁剪。

专家级作品集建议：

- 一个从零实现的迷你搜索引擎。
- 一个基于 Elasticsearch / OpenSearch / Vespa 的真实搜索服务。
- 一套离线评估集和评估报告。
- 一个 LTR 或 hybrid search 的可复现实验。
- 一份生产级搜索系统设计文档。
- 至少 3 篇搜索 bad case 深度复盘。

## 3. 推荐项目顺序

1. `mini-search`：从零实现倒排索引和 BM25。
2. `product-search`：Elasticsearch / OpenSearch 商品搜索。
3. `search-eval-lab`：评估集、NDCG、bad case 分析。
4. `ltr-reranker`：XGBoost / LightGBM 排序模型。
5. `hybrid-search`：BM25 + dense vector + RRF。
6. `distributed-search-design`：千万级搜索系统设计和压测。

每个项目都保留：

- README：问题、方案、运行方式。
- DESIGN：架构和关键取舍。
- EVAL：指标、实验结果、bad case。
- NOTES：读源码和读论文笔记。

## 4. 工具栈建议

入门阶段：

- Python
- SQLite / JSONL
- Jupyter Notebook
- pytest

工程阶段：

- Java 或 Python
- Lucene
- Elasticsearch 或 OpenSearch
- Docker Compose
- FastAPI / Spring Boot
- k6 / JMeter / Rally

高级阶段：

- Faiss
- Vespa
- XGBoost / LightGBM
- Pyserini / Anserini
- Hugging Face sentence-transformers
- MLflow 或简单实验记录系统

## 5. 每周学习节奏

建议每周 10-15 小时：

- 30% 理论：读书、课程、论文。
- 50% 编码：实现、调参、压测、debug。
- 20% 复盘：写实验报告、bad case、设计文档。

每周固定产出：

- 1 个可运行增量。
- 1 份实验数据。
- 1 篇短复盘：这周搜索质量为什么变好或变差。

## 6. 关键判断标准

你可以按下面标准判断自己是否逐步接近资深水平：

- 初级：能搭建 Elasticsearch 搜索接口，能配置 analyzer 和 mapping。
- 中级：能解释倒排索引、BM25、分片、副本、explain、profile。
- 高级：能建立评估集，系统性优化相关性和延迟。
- 资深：能设计完整搜索架构，平衡质量、成本、延迟、可用性。
- 专家：能改搜索引擎内核、设计评估体系、推动业务搜索质量长期迭代。

## 7. 下一步建议

当前目录还没有 Git 仓库。建议先不要急着初始化，等第一个项目结构确定后再执行 `git init`。第一步可以从 `mini-search` 开始：

1. 建立 `data/`、`src/`、`tests/`、`docs/`。
2. 准备 1000 篇文档。
3. 实现 tokenizer 和倒排索引。
4. 实现 BM25。
5. 写 20 个 query 的人工评估表。

