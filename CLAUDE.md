# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## General Instructions for Claude Code
### Token Conservation Rules (High Priority)
1. **NO SUB-AGENTS**: Do not spawn sub-agents for exploration or planning. Perform all tasks in the main thread to avoid redundant discovery costs.
2. **NO RECURSIVE GREP**: Do not use `grep -r` on the project root. Use `ls -R` first to map directory structure before targeted searching.
3. **PREFER AST-GREP**: Always use the `sg` (ast-grep) tool for code searches instead of standard grep.
4. **SESSION HYGIENE**: Use the `/clear` command aggressively between tasks to prevent search history from bloating the context window.

### Preferred Search Strategy
* **Step 1**: Use `ls -R` to identify relevant directories.
* **Step 2**: Use `sg` (ast-grep) for structural search (e.g., finding class definitions or function signatures).
* **Step 3**: Only use `grep` as a last resort for plain-text matches within a specific, small directory.

### Locations
- Services are in src/main/java/eterea/core/service/service
- Controllers are in src/main/java/eterea/core/service/controller
- Repositories are in src/main/java/eterea/core/service/repository
- Entities are in src/main/java/eterea/core/service/model and src/main/kotlin/eterea/core/service/kotlin/model
- Exceptions are in src/main/java/eterea/core/service/controller/exception
- DTOs are in src/main/java/eterea/core/service/model/dto and src/main/kotlin/eterea/core/service/kotlin/model/dto

### Others
- Remain critical and skeptical about my thinking at all times. Maintain consistent intellectual standars thoughout our conversation. Don't lower your bar for evidence or reasoning quality just because we've been talking longer or because I seem frustrated.
- Don't assume I'm always right, give me the pros and cons of the solutions I suggest.
- Don't try to run commands or compile/execute-tests, tell me to run them and I'll do it myself.
- If it's possible use functional programming.