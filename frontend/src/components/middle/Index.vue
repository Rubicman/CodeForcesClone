<template>
    <div>
        <IndexPost v-for="post in viewedPosts" :post="post" :key="post.id"/>
    </div>
</template>

<script>
    import IndexPost from "./IndexPost";

    export default {
        name: "Index",
        components: {IndexPost},
        props: ["posts", "users"],
        computed: {
            viewedPosts: function () {
                return Object.values(this.posts).sort((a, b) => b.id - a.id);
            }
        },
        comments: {
            IndexPost
        }
    }
</script>

<style scoped>

</style>
