window.addEventListener('load', function(){
        var h = window.innerHeight, w;
        var svg = d3.select( '#render' )
            .append( 'svg' );

        function getWidth(){
            w = window.innerWidth;
            svg.attr( { width: w, height: h } );
            d3.select( '#border' ).attr( 'width', w );
        }
        getWidth();
        window.addEventListener( 'resize', getWidth );
    
        var arc = d3.svg.arc();
        
        function arcTween( transition ) {
            transition.attrTween( 'd', function( d ) {
                var start = ~~( Math.random() * 36 ) * 10 / 180 * Math.PI;

                var interpolateStart = d3.interpolate( d.startAngle, start );
                var interpolateEnd = d3.interpolate( d.endAngle, start + ~~( 1 + Math.random() * 18 ) * 10 / 180 * Math.PI  - Math.PI);
                return function( t ) {
                    d.startAngle = interpolateStart( t );
                    d.endAngle = interpolateEnd( t );
                    return arc( d );
                };
            });
        }

        var paths, nb, n;
        function init(){
            n = 1 + ~~( Math.random() * 10 );
            nb = 150 + ~~( Math.random() * 250 );
            paths = svg.selectAll( 'path' )
                .remove()
                .data( d3.range( nb ).map( function(){
                    var inRad = 100 + ~~( Math.random() * 45 ) * 10;

                    return {
                        startAngle: 0,
                        endAngle: 0,
                        innerRadius: inRad,
                        outerRadius: inRad + 10.1
                    };
                } ) )
                .enter()
                .append( 'path' )
                .attr( 'd', arc )
                .attr( 'fill', 'rgba( 251, 53, 20, 0.8 )' )
                .attr( 'opacity', function( d ){
                    return ~~( Math.random() * 10 ) / 10;
                } )
                .attr( 'transform', 'translate(' + ( w / 2 ) + ',' + ( h / 2 ) + ')' );

            anim();
        }

        function anim(){
            var count = 0;
            paths
                .transition()
                .duration( 1000 )
                .delay( function( d, i ){
                    return i * 5;
                } )
                .call( arcTween )
                .transition()
                .duration( 1000 )
                .each( 'end', function( d ){
                    count ++;
                    if( count === nb ){
                        n --;
                        if( n === 0){
                            terminate();
                        }
                        else{
                            anim();
                        }
                    }
                } );
        }

        init();

        function arcTerminate( transition ) {
            transition.attrTween( 'd', function( d ) {
                var interpolateStart = d3.interpolate( d.startAngle, Math.PI );
                var interpolateEnd = d3.interpolate( d.endAngle, Math.PI );
                return function( t ) {
                    d.startAngle = interpolateStart( t );
                    d.endAngle = interpolateEnd( t );
                    return arc( d );
                };
            });
        }

        function terminate(){
            var count = 0;
            paths
                .transition()
                .duration( 1000 )
                .call( arcTerminate )
                .remove()
                .each( 'end', function( d ){
                    count ++;
                    if( count === nb ) init();
                } );
        }

        svg.node().addEventListener('click', terminate );
    } );