# Java Mini Search 学习计划

这个分支从 Java 版本的 `mini-search` 开始。目标不是直接使用 Lucene 或 Elasticsearch，而是先自己实现核心概念，再对照成熟搜索引擎。

## 文档拆分原则

根目录 `README.md` 只保留入口信息。长期学习路线、阶段说明和维护规则都放在 `docs/` 下：

- [文档索引](README.md)
- [练习维护规则](MAINTAINING_EXERCISES.md)
- [Stage 01: Baseline Search](stages/stage-01-baseline-search.md)
- [Stage 02: Inverted Index](stages/stage-02-inverted-index.md)
- [Stage 03: Ranking Models](stages/stage-03-ranking-models.md)
- [Stage 04: Search Evaluation](stages/stage-04-search-evaluation.md)

## 第一阶段：线性扫描基线

已完成起步代码：

- `Document`：文档模型。
- `SimpleTokenizer`：小写化和字母数字分词。
- `LinearSearchEngine`：全量扫描文档并按简单分数排序。
- `Exercise` / `ExerciseCatalog`：练习目录，逐步引导学习。
- `ExerciseCategory`：按阶段分类练习，避免后续内容堆在一起。
- `Exercise01LinearScan`：第一个练习，输出查询耗时和 Top 结果。
- `Searchlings`：稳定的可点击运行入口，IDE 里直接运行这个类。
- `Main`：命令解析和分类调度。
- `SLF4J + Logback`：向控制台输出结构化日志，替代 `System.out`。

源码目录按学习顺序拆分：

- `me.catcodeme.search.app`：命令行入口。
- `me.catcodeme.search.core`：搜索核心模型和算法。
- `me.catcodeme.search.corpus`：样本文档和后续数据加载。
- `me.catcodeme.search.exercises`：引导式练习。

运行方式：

- IDE 点击运行：`me.catcodeme.search.app.Searchlings`
- IDEA 运行配置：`Searchlings - Default`、`Searchlings - List`、`Searchlings - Stage 01` 到 `Searchlings - Stage 04`

Codex 后续维护规则见 [MAINTAINING_EXERCISES.md](MAINTAINING_EXERCISES.md)。

当前评分规则：

- 标题命中 1 次加 5 分。
- 正文命中 1 次加 1 分。

这个基线故意简单。后续所有优化都要和它对比，才能看清倒排索引、BM25 和重排到底解决了什么问题。

## 下一步任务

1. 为 `SimpleTokenizer` 和 `LinearSearchEngine` 增加测试。
2. 支持从 `data/documents.jsonl` 加载文档。
3. 实现倒排索引：`term -> postings`。
4. 在同一批数据上对比线性扫描和倒排索引的查询耗时。
5. 实现 TF-IDF。
6. 实现 BM25，并输出 explain 信息。

## 每次实验记录

每完成一个小功能，记录：

- 数据规模。
- 查询样例。
- Top 结果。
- 查询耗时。
- 这次改动让搜索质量或性能发生了什么变化。
