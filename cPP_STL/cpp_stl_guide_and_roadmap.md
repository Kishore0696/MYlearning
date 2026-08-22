# C++ STL — Detailed Guide & Learning Roadmap

## 1. What is the STL?

The **Standard Template Library (STL)** is a collection of C++ template classes and functions that implement common data structures and algorithms in a generic, reusable, and highly efficient way. It's part of the C++ Standard Library and ships with every standard-compliant compiler.

The STL is built on **generic programming** using templates, meaning the same container or algorithm works with `int`, `std::string`, custom classes, or almost any type, without rewriting code.

### Why use it?
- **Battle-tested & optimized** — implementations are highly tuned (often better than what you'd write by hand).
- **Saves time** — no need to write your own linked list, hash table, sorting algorithm, etc.
- **Consistency** — a uniform interface (iterators) lets algorithms work across different containers.
- **Type-safe** — templates catch type errors at compile time.

---

## 2. The Four Pillars of STL

The STL is organized into four major components that work together:

### A. Containers
Data structures that store collections of objects.

| Category | Containers | Description |
|---|---|---|
| **Sequence containers** | `vector`, `deque`, `list`, `forward_list`, `array` | Store elements in linear order |
| **Associative containers** | `set`, `multiset`, `map`, `multimap` | Store sorted key-based elements (usually via balanced trees, e.g., Red-Black Tree) |
| **Unordered associative containers** | `unordered_set`, `unordered_multiset`, `unordered_map`, `unordered_multimap` | Hash-table-based, average O(1) lookup |
| **Container adapters** | `stack`, `queue`, `priority_queue` | Restricted interfaces built on top of other containers |

**Quick cheat sheet:**
- `vector` → dynamic array, fast random access, fast push_back (amortized O(1))
- `list` → doubly linked list, fast insert/delete anywhere, no random access
- `deque` → double-ended queue, fast insert/delete at both ends
- `set`/`map` → sorted, O(log n) operations, implemented as balanced BST (typically Red-Black Tree)
- `unordered_set`/`unordered_map` → hash table, average O(1) operations
- `stack` → LIFO, built on `deque` by default
- `queue` → FIFO, built on `deque` by default
- `priority_queue` → max-heap by default, built on `vector`

### B. Algorithms
Generic functions that operate on ranges of elements via iterators (defined mostly in `<algorithm>` and `<numeric>`).

Examples:
- Sorting: `sort()`, `stable_sort()`, `partial_sort()`
- Searching: `find()`, `binary_search()`, `lower_bound()`, `upper_bound()`
- Modifying: `reverse()`, `rotate()`, `unique()`, `remove()`
- Numeric: `accumulate()`, `inner_product()`, `partial_sum()`
- Set operations: `set_union()`, `set_intersection()`, `set_difference()`
- Min/Max: `min_element()`, `max_element()`, `minmax_element()`

These algorithms are **container-agnostic** — the same `sort()` works on a `vector<int>` or a `deque<string>` because it operates through iterators, not the container directly.

### C. Iterators
The "glue" between containers and algorithms. An iterator is like a generalized pointer that lets you traverse a container without knowing its internal structure.

| Iterator Type | Capability | Example Containers |
|---|---|---|
| Input Iterator | Read, single-pass, forward | istream_iterator |
| Output Iterator | Write, single-pass, forward | ostream_iterator |
| Forward Iterator | Read/write, multi-pass, forward | forward_list |
| Bidirectional Iterator | Forward + backward | list, set, map |
| Random Access Iterator | Jump to any position (i + n) | vector, deque, array |

Common syntax:
```cpp
std::vector<int> v = {1, 2, 3};
for (auto it = v.begin(); it != v.end(); ++it) {
    std::cout << *it << " ";
}
```

### D. Functors (Function Objects) & Lambdas
Objects that behave like functions (overload `operator()`), used to customize algorithm behavior.

```cpp
// Functor example
struct Greater {
    bool operator()(int a, int b) { return a > b; }
};
std::sort(v.begin(), v.end(), Greater());

// Modern equivalent: lambda
std::sort(v.begin(), v.end(), [](int a, int b) { return a > b; });
```

Standard functors live in `<functional>`: `std::greater<>`, `std::less<>`, `std::plus<>`, etc.

---

## 3. Core Headers You'll Use

| Header | Purpose |
|---|---|
| `<vector>`, `<deque>`, `<list>`, `<array>`, `<forward_list>` | Sequence containers |
| `<set>`, `<map>` | Ordered associative containers |
| `<unordered_set>`, `<unordered_map>` | Hash-based containers |
| `<stack>`, `<queue>` | Container adapters |
| `<algorithm>` | Sorting, searching, transforming |
| `<numeric>` | Numeric operations (accumulate, gcd, etc.) |
| `<iterator>` | Iterator utilities |
| `<functional>` | Function objects, `std::function`, `std::bind` |
| `<utility>` | `std::pair`, `std::move`, `std::swap` |
| `<tuple>` | `std::tuple` for multiple heterogeneous values |

---

## 4. Example: Putting It All Together

```cpp
#include <iostream>
#include <vector>
#include <algorithm>

int main() {
    std::vector<int> nums = {5, 2, 8, 1, 9, 3};

    // Algorithm + iterator
    std::sort(nums.begin(), nums.end());

    // Range-based for loop (uses iterators internally)
    for (int n : nums) std::cout << n << " ";
    std::cout << "\n";

    // Binary search requires sorted range
    bool found = std::binary_search(nums.begin(), nums.end(), 8);
    std::cout << "Found 8? " << found << "\n";

    // Lambda with algorithm
    int count = std::count_if(nums.begin(), nums.end(), [](int n) { return n % 2 == 0; });
    std::cout << "Even numbers: " << count << "\n";
}
```

---

## 5. Time Complexity Cheat Sheet

| Container | Access | Search | Insert | Delete |
|---|---|---|---|---|
| vector | O(1) | O(n) | O(1) amortized at end, O(n) elsewhere | O(n) |
| deque | O(1) | O(n) | O(1) at ends | O(1) at ends |
| list | O(n) | O(n) | O(1) | O(1) |
| set/map | O(log n) | O(log n) | O(log n) | O(log n) |
| unordered_set/map | O(1) avg | O(1) avg | O(1) avg | O(1) avg |
| priority_queue | — | — | O(log n) | O(log n) for pop |

---

# 📍 Roadmap to Master C++ STL

## Phase 1 — Foundations (Prerequisite, ~1 week)
- [ ] Solid understanding of C++ basics: pointers, references, functions
- [ ] Templates (function templates & class templates)
- [ ] Basics of Object-Oriented Programming (classes, operator overloading)
- [ ] Understand `const`, references (`&`), and pass-by-reference vs value

## Phase 2 — Sequence Containers (~1 week)
- [ ] `std::vector` — the most-used container; master `push_back`, `pop_back`, `resize`, `capacity` vs `size`, 2D vectors
- [ ] `std::array` — fixed-size array wrapper
- [ ] `std::deque` — double-ended operations
- [ ] `std::list` / `std::forward_list` — linked list operations, when to prefer over vector

## Phase 3 — Iterators (~3-4 days)
- [ ] `begin()`, `end()`, `rbegin()`, `rend()`, `cbegin()`, `cend()`
- [ ] Iterator categories and which containers support which
- [ ] Iterator invalidation rules (critical for avoiding bugs!)
- [ ] Range-based for loops vs explicit iterators

## Phase 4 — Associative Containers (~1 week)
- [ ] `std::set` / `std::multiset`
- [ ] `std::map` / `std::multimap` — key-value pairs, `operator[]` vs `at()` vs `find()`
- [ ] `std::unordered_set` / `std::unordered_map` — hashing basics, custom hash functions
- [ ] When to choose ordered vs unordered (sorted output? need O(1)? custom key type?)

## Phase 5 — Container Adapters (~2-3 days)
- [ ] `std::stack` — LIFO use cases (parentheses matching, DFS, undo systems)
- [ ] `std::queue` — FIFO use cases (BFS, task scheduling)
- [ ] `std::priority_queue` — heaps, custom comparators, min-heap vs max-heap

## Phase 6 — Algorithms Library (~2 weeks, the big one)
- [ ] Non-modifying: `find`, `count`, `for_each`, `all_of`/`any_of`/`none_of`
- [ ] Modifying: `copy`, `transform`, `replace`, `fill`, `remove`/`remove_if` (learn the erase-remove idiom!)
- [ ] Sorting: `sort`, `stable_sort`, `partial_sort`, `nth_element`
- [ ] Binary search family: `binary_search`, `lower_bound`, `upper_bound`, `equal_range`
- [ ] Set algorithms: `set_union`, `set_intersection`, `set_difference`
- [ ] Numeric: `accumulate`, `reduce`, `inner_product`, `gcd`, `lcm`
- [ ] Permutations: `next_permutation`, `prev_permutation`
- [ ] Heap algorithms: `make_heap`, `push_heap`, `pop_heap`

## Phase 7 — Functors, Lambdas & Function Objects (~1 week)
- [ ] Writing custom comparators (functors and lambdas)
- [ ] `std::function` for storing any callable
- [ ] `std::bind` (and why lambdas usually replace it now)
- [ ] Standard functors: `std::greater`, `std::less`, `std::plus`

## Phase 8 — Utility Types (~3-4 days)
- [ ] `std::pair` and `std::make_pair`
- [ ] `std::tuple`, `std::tie`, structured bindings (`auto [a, b] = pair`)
- [ ] `std::optional` (C++17)
- [ ] `std::variant` (C++17)

## Phase 9 — Advanced / Modern C++ with STL (~2 weeks)
- [ ] Move semantics with STL containers (`std::move`, emplace vs insert)
- [ ] `emplace_back` vs `push_back` (avoiding unnecessary copies)
- [ ] Custom allocators (awareness level)
- [ ] Ranges library (C++20) — a modern, composable way to use algorithms
- [ ] `std::span` (C++20)
- [ ] Writing your own STL-compatible container/iterator (deep understanding check)

## Phase 10 — Practice & Application (Ongoing)
- [ ] Solve 50+ problems on LeetCode/Codeforces using STL containers idiomatically
- [ ] Reimplement a small STL container (e.g., a simplified vector) to understand internals
- [ ] Study STL source (e.g., libstdc++ or MSVC STL) for containers you use most
- [ ] Learn to profile: know when STL overhead matters vs when it doesn't

---

## 6. Suggested Timeline

| Weeks | Focus |
|---|---|
| Week 1 | Foundations + sequence containers |
| Week 2 | Iterators + associative containers |
| Week 3-4 | Algorithms library (the largest, most important part) |
| Week 5 | Functors/lambdas + utility types |
| Week 6+ | Advanced topics + heavy practice (competitive programming problems) |

## 7. Best Resources
- **cppreference.com** — the definitive reference for every container/algorithm signature
- **"Effective STL" by Scott Meyers** — classic book on STL best practices and pitfalls
- **LeetCode / Codeforces** — practice applying STL under real constraints
- **cplusplus.com** — good for quick lookups (slightly less rigorous than cppreference)

---

### Golden Rule
Don't memorize every function — learn the **mental model** (containers = storage, iterators = glue, algorithms = generic operations) and use cppreference as your lookup tool. Fluency comes from writing code, not memorizing signatures.
