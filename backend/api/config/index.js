// Global App Configuration
module.exports = {
	SECRET: "32876qihsdh76@&#!742(*#HG&#28702y&##@^!()(&^#))jhscbd",
	MONGO_URI:
		process.env.NODE_ENV === "production"
			? "mongodb://localhost:27017/JournalTask"
			: "mongodb://localhost:27017/JournalTask",
    EMAIL_REGEX: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
};
