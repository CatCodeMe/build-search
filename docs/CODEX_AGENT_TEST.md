# Codex Agent Test

Quick guide for using Codex from GitHub PR comments.

## 1) Set up once
- In Codex Cloud, create an environment for this repository and install dependencies/tests your repo needs.
- Make sure the environment can access the PR branch.

## 2) Ask Codex from the PR
- In a PR comment, mention `@codex` and give a direct task.
- Keep requests specific (file + expected outcome).

Example:

```text
@codex update docs/CODEX_AGENT_TEST.md with a 5-bullet quickstart for contributors.
```

## 3) Iterate quickly
- If the result needs changes, reply in the same PR thread with follow-ups.
- Use explicit instructions like “shorter,” “add test steps,” or “only edit this file.”

Example follow-up:

```text
@codex revise: keep it under 120 words and include one command example.
```
