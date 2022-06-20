# substantial

> How can I be substantial if I do not cast a shadow? I must have a dark side also If I am to be whole.
>
> &mdash; Carl Jung

## Project structure

## Metadata

- `heading` (required): The main heading.
- `id` (required): The unique short name (slug) displayed in the url used to link to the note.
- `date` (required): The publication date.
- `tags`: A list of tags used to categorize the note.

Example:
```markdown
heading: This is Note 1
id: note-1
date: 2019-12-01
```

## Publish

Create a [Github Pages](https://pages.github.com/) site to publish.

- `git subtree push -f --prefix public origin gh-pages`