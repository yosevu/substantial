# substantial

> How can I be substantial if I do not cast a shadow? I must have a dark side also If I am to be whole.
>
> &mdash; Carl Jung

A Markdown static site generator that supports backlink references.

- Transforms Markdown files to HTML.
- Adds backlink references to markdown files.
- Aggregates backlink references for markdown file.
- Written in [Clojure](https://clojure.org/).
- Uses [Hiccup](https://github.com/weavejester/hiccup) for HTML templating.

### Example Sites

- https://notes.yosevu.com/

## Usage

### Metadata

- `heading` (required): The main heading.
- `id` (required): The unique short name (slug) displayed in the url used to link to the note.
- `date` (required): The publication date.

Example:
```markdown
heading: This is Note 1
id: note-1
date: 2019-12-01
```

### Build Site

```
clj -M:build
```

### Publish Site

Create a [Github Pages](https://pages.github.com/) site to publish.

```
git push origin `git subtree split --prefix public main`:gh-pages --force
```

## Local Development


### Project Structure

```
.
├── README.md
├── deps.edn
├── notes
│   ├── index.md
│   ├── note.md
├── public
│   ├── CNAME
│   ├── css
│   │   ├── a11y-dark.min.css
│   │   └── main.css
│   ├── note.html
│   ├── index.html
│   ├── js
│   │   └── highlight.min.js
├── src
│   ├── substantial
│   │   ├── backlinks.clj
│   │   ├── core.clj
│   │   ├── metadata.clj
│   │   ├── notes.clj
│   │   └── utilities.clj
│   └── templates
│       ├── note.clj
│       └── partials.clj
├── test
│   ├── backlink-test.md
│   └── substantial
│       └── core_test.clj
```

### [Calva](https://calva.io/)

- `Calva: Load/Evaluate Current File and its Requires/Dependencies`
