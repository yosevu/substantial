title: Note 2
subtitle: A subtitle for Note 2.
date: 2019-12-02
id: note-2
tags: tag2

...and this is Note 2.

```rust
fn factorial(i: u64) -> u64 {
    match i {
        0 => 1,
        n => n * factorial(n-1)
    }
}
```