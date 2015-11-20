$(document)getready(function(){
$('#calendar').fullCalendar({
    events: function(start, end, timezone, callback) {
        $.ajax({
            url: 'calendar.jsp',
            dataType: 'xml',
            data: {
                start: start.unix(),
                end: end.unix()
            },
            success: function(doc) {
                var events = [];
                $(doc).find('event').each(function() {
                    events.push({
                        title: $(this).attr('title'),
                        start: $(this).attr('start') 
                    });
                });
                callback(events);
            }
        });
    }
});
});

